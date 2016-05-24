package it.codegen.config;

import it.codegen.data.Patch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thilanka on 5/24/2016.
 */
@Component
public class DataManager
{
    @Autowired
    private DataConfig dataConfig;

    public List<Patch> searchPatches()
    {
        List<Patch> patches = new ArrayList<>(  );
        String sql = "SELECT * FROM tbx_patch where author = ?";

        Connection con = null;
        try
        {
            con = dataConfig.dataSource().getConnection();
            PreparedStatement ps = con.prepareStatement( sql );

            // TODO pass this param
            ps.setString( 1, "thilanka" );
            ResultSet rs = ps.executeQuery();
            while( rs.next() )
            {
                Patch patch = new Patch();
                patch.setAuthor( rs.getString( "author" ) );
                patch.setProject( rs.getString( "project" ) );

                patches.add( patch );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if( con != null )
            {
                try
                {
                    con.close();
                }
                catch( SQLException e )
                {
                }
            }
        }
        return patches;
    }
}
