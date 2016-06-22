package it.codegen;

import java.io.OutputStream;

/**
 * Created by thilanka on 6/20/2016.
 */
public interface RepoManager
{
    public void createPatch( String branch, long startRevision, long endRevision);
}
