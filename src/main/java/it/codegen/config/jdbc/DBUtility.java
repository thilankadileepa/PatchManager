package it.codegen.config.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by thilanka on 6/20/2016.
 */
public class DBUtility
{
    public static void close( Connection con )
    {

        try
        {
            if( con != null && !con.isClosed() )
            {
                con.close();
            }
        }
        catch( SQLException var2 )
        {
            var2.printStackTrace();
        }

    }

    public static void close( ResultSet rs )
    {
        try
        {
            if( rs != null )
            {
                rs.close();
            }
        }
        catch( SQLException var2 )
        {
            var2.printStackTrace();
        }

    }

    public static void close( Statement statement )
    {
        try
        {
            if( statement != null )
            {
                statement.close();
            }
        }
        catch( SQLException var2 )
        {
            var2.printStackTrace();
        }

    }
}
