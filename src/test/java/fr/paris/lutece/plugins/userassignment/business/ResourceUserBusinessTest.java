package fr.paris.lutece.plugins.userassignment.business;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.test.LuteceTestCase;

public class ResourceUserBusinessTest extends LuteceTestCase
{
    public void testCRUD( )
    {
        ResourceUser resourceUser = new ResourceUser( );
        resourceUser.setIdResource( 11 );
        resourceUser.setResourceType( "toto" );
        resourceUser.getAdminUser( ).setUserId( 1 );
        resourceUser.setDateAssignment( Timestamp.valueOf( LocalDateTime.now( ) ) );
        resourceUser.setActive( true );
        
        ResourceUserHome.create( resourceUser );
        
        List<ResourceUser> list = ResourceUserHome.findByUser( 1, "toto" );
        
        assertEquals( 1, list.size( ) );
        assertEquals( resourceUser.getIdResource( ), list.get( 0 ).getIdResource( ) );
        assertEquals( resourceUser.getResourceType( ), list.get( 0 ).getResourceType( ) );
        assertEquals( resourceUser.getDateAssignment( ), list.get( 0 ).getDateAssignment( ) );
        assertTrue( list.get( 0 ).isActive( ) );
        
        ResourceUserHome.deactivate( resourceUser );
        
        list = ResourceUserHome.findByUser( 1, "toto" );
        
        assertEquals( 1, list.size( ) );
        assertEquals( resourceUser.getIdResource( ), list.get( 0 ).getIdResource( ) );
        assertEquals( resourceUser.getResourceType( ), list.get( 0 ).getResourceType( ) );
        assertFalse( list.get( 0 ).isActive( ) );
        
        ResourceUserHome.deleteByResource( resourceUser.getIdResource( ), resourceUser.getResourceType( ) );
        
        list = ResourceUserHome.findByUser( 1, "toto" );
        
        assertEquals( 0, list.size( ) );
    }
}
