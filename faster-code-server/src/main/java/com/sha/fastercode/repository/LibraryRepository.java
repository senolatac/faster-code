package com.sha.fastercode.repository;

import com.sha.fastercode.model.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LibraryRepository  extends CrudRepository<Library, Long> {
}
