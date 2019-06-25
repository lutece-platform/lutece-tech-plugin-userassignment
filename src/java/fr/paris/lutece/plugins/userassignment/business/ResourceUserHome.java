package fr.paris.lutece.plugins.userassignment.business;

import java.util.List;

import fr.paris.lutece.plugins.userassignment.service.UserassignmentPlugin;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public final class ResourceUserHome {

	private static Plugin _plugin = PluginService.getPlugin( UserassignmentPlugin.PLUGIN_NAME );
	 
	private static IResourceUserDAO _dao = SpringContextService.getBean( IResourceUserDAO.BEAN_NAME );
	 
	private ResourceUserHome( )
	{
	}
	
	/**
     * Creates a resource assignment
     * 
     * @param resourceUser
     *            The resource assignment to create
     * @return The resource assignment which has been created
     */
    public static ResourceUser create( ResourceUser resourceUser )
    {
        _dao.insert( resourceUser, _plugin );

        return resourceUser;
    }

    /**
     * Deactivates a resource assignment
     * 
     * @param resourceUser
     *            The resource assignment to deactivate
     */
    public static void deactivate( ResourceUser resourceUser )
    {
        _dao.deactivateByUserResource( resourceUser.getAdminUser( ).getUserId( ), resourceUser.getIdResource( ), resourceUser.getResourceType( ), _plugin );
    }

    /**
     * <p>
     * Finds the users associated to the specified couple {resource id, resource type}.
     * </p>
     * <p>
     * The result list contains active and inactive resource assignments.
     * </p>
     * 
     * @param nIdResource
     *            the resource id
     * @param strResourceType
     *            the resource type
     * @return the list of unit assignments
     */
    public static List<AdminUser> findUserByResource( int nIdUnit, String strResourceType )
    {
        return _dao.selectUserListByResource( nIdUnit, strResourceType, _plugin );
    }

    /**
     * <p>
     * Finds the resource assignments associated to the specified user.
     * </p>
     * <p>
     * The result list contains active and inactive resource assignments.
     * </p>
     * 
     * @param nIdUser
     *            the user id
     * @return the list of respurce assignments
     */
    public static List<ResourceUser> findByUser( int nIdUser, String resourceType )
    {
        return _dao.selectResourcesByUser( nIdUser, resourceType, _plugin );
    }

    /**
     * <p>
     * Deactivate the resource assignments associated to the specified resource.
     * </p>
     * 
     * @param userId
     * @param resourceID
     *            the resource id
     * @param resourceType
     *            the resource type
     */
    public static void deactivateByUserResource( int userId, int resourceID, String resourceType )
    {
        _dao.deactivateByUserResource( userId, resourceID, resourceType, _plugin);
    }
}
