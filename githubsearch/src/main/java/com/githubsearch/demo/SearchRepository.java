package com.githubsearch.demo;

import org.springframework.data.repository.CrudRepository;

public interface SearchRepository extends CrudRepository<Search, String>
{
	public Search findByOrgFileExt(String orgFileExt); 
}
