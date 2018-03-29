package com.githubsearch.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService 
{
	@Autowired
	private SearchRepository searchRepo;
	
	public void save(Search search)
	{
		searchRepo.save(search);
	}
	
	public Search get(String id)
	{
		return searchRepo.findOne(id);
	}
	
	public Search getByOrgFileExt(String orgAndFileExt)
	{
		return searchRepo.findByOrgFileExt(orgAndFileExt);
	}
}
