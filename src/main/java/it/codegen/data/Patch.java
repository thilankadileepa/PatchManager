package it.codegen.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by thilanka on 5/24/2016.
 */
public class Patch
{
    private long number;
    private String project;
    private String author;
    private String branch;
    private String revision;
    private String bug;
    private String comment;

    private Date date;

    public void load( ResultSet rs) throws SQLException
    {
        this.setAuthor( rs.getString( "author" ) );
        this.setProject( rs.getString( "project" ) );
    }

    public long getNumber()
    {
        return number;
    }

    public void setNumber( long number )
    {
        this.number = number;
    }

    public String getProject()
    {
        return project;
    }

    public void setProject( String project )
    {
        this.project = project;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }

    public String getBranch()
    {
        return branch;
    }

    public void setBranch( String branch )
    {
        this.branch = branch;
    }

    public String getRevision()
    {
        return revision;
    }

    public void setRevision( String revision )
    {
        this.revision = revision;
    }

    public String getBug()
    {
        return bug;
    }

    public void setBug( String bug )
    {
        this.bug = bug;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment( String comment )
    {
        this.comment = comment;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    @Override public String toString()
    {
        return "Patch{" +
                "number=" + number +
                ", project='" + project + '\'' +
                ", author='" + author + '\'' +
                ", branch='" + branch + '\'' +
                ", revision='" + revision + '\'' +
                ", bug='" + bug + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
