package com.githubsearch.demo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SearchController 
{
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value="/getsearch/{org}/{fileExt}")
	public String searchFileReferences(@PathVariable String org, @PathVariable("fileExt") String ext)
	{
		try
		{
			Search vRetObj = searchService.getByOrgFileExt(org+ext);
			String vJsonStr = null;
			if(vRetObj!=null)
			{
				vJsonStr = vRetObj.getFileRefSource();
			}
			return vJsonStr;
		 } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/githubsearch")
	private void saveAndSearchFileReferences(@RequestBody Search search)
	{
		try
		{
		String baseUrl = "https://api.github.com/search/code";
		RestInvoker vRstInvker = new RestInvoker(baseUrl);
		String org = search.getOrg();
		String fileExt = search.getFileExt();
		
		if(org != null && !org.isEmpty()
			&& fileExt!=null && !fileExt.isEmpty())
		{
			String[] orgArr = org.split(",");
			String[] fileExtArr = fileExt.split(",");
			if(orgArr.length > 0 && fileExtArr.length > 0)
			{
				for(String tmpOrg : orgArr)
				{					
					for(String tmpFileExt : fileExtArr)
					{
						Search tmpSearch = new Search();
						String jsonStr = vRstInvker.getDataFromServer("?q=addClass+org:"+tmpOrg+"+extension:"+tmpFileExt);
						System.out.println(jsonStr);
						
						JSONObject jsonObject = new JSONObject(jsonStr);
						Integer totalcount = (Integer)jsonObject.get("total_count");
						System.out.println("Total Count : " + totalcount);
						
						if(totalcount.compareTo(0) > 0)
						{
							Search existingSearch = searchService.getByOrgFileExt(tmpOrg+tmpFileExt);
							if(existingSearch != null)
								tmpSearch.setId(existingSearch.getId());
							tmpSearch.setOrgFileExt(tmpOrg+tmpFileExt);
							tmpSearch.setFileRefSource(jsonStr);
							searchService.save(tmpSearch);
						}
					}
				}
			}
		}
		
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
	}
}
