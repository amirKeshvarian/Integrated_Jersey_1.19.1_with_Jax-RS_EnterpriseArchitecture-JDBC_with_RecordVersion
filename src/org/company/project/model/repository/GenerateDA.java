package org.company.project.model.repository;

public interface GenerateDA extends AutoCloseable{
     void commit() throws Exception;
}
