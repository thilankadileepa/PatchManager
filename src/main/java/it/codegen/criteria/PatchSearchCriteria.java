package it.codegen.criteria;

/**
 * Created by thilanka on 6/20/2016.
 */
public class PatchSearchCriteria
{
    private String project;
    private String revision;
    private String author;

    // TODO other search parameters


    public String getProject()
    {
        return project;
    }

    public void setProject( String project )
    {
        this.project = project;
    }

    public String getRevision()
    {
        return revision;
    }

    public void setRevision( String revision )
    {
        this.revision = revision;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }
}
