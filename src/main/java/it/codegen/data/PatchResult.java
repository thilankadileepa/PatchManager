package it.codegen.data;

import java.util.List;
import java.util.StringJoiner;

/**
 * Created by thilanka on 6/20/2016.
 */
public class PatchResult
{
    private String filePath;
    private List<Patch> revisions;
    private List<String> classes;

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath( String filePath )
    {
        this.filePath = filePath;
    }

    public List<Patch> getRevisions()
    {
        return revisions;
    }

    public void setRevisions( List<Patch> revisions )
    {
        this.revisions = revisions;
    }

    public List<String> getClasses()
    {
        return classes;
    }

    public void setClasses( List<String> classes )
    {
        this.classes = classes;
    }
}
