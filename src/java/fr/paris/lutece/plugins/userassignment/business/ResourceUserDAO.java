/*
 * Copyright (c) 2002-2020, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.userassignment.business;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.business.user.AdminUserHome;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * This class provides Data Access methods for {@link ResourceUser} objects
 */

public class ResourceUserDAO implements IResourceUserDAO
{

    public static final String BEAN_NAME = "userassignment.resourceUserDAO";
    // Queries
    private static final String SQL_INSERT = "INSERT INTO userassignment_resource_user (id, id_resource, resource_type, id_user, assignment_date, is_active ) VALUES (?,?,?,?,?,?) ";
    private static final String SQL_DELETE = "DELETE FROM userassignment_resource_user WHERE id = ? ";
    private static final String SQL_SELECT_RESOURCE_BY_USER = "SELECT id, id_resource, resource_type, id_user, assignment_date, is_active FROM userassignment_resource_user WHERE id_user = ? AND resource_type = ? ";
    private static final String SQL_SELECT_USER_BY_RESOURCE = "SELECT id_user FROM userassignment_resource_user WHERE id_resource = ? AND resource_type = ? AND is_active = 1 ";
    private static final String SQL_SELECT_USER_BY_RESOURCE_TYPE = "SELECT id_user FROM userassignment_resource_user WHERE resource_type = ? AND is_active = 1 ";
    private static final String SQL_DEACTIVATE_BY_USER_BY_RESOURCE = "UPDATE userassignment_resource_user set is_active = 0 WHERE id_user = ? AND id_resource = ? AND resource_type = ? ";
    private static final String SQL_DELETE_BY_RESOURCE = "DELETE FROM userassignment_resource_user WHERE id_resource = ? AND resource_type = ? ";

    @Override
    public void insert( ResourceUser resource, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_INSERT, Statement.RETURN_GENERATED_KEYS, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, resource.getId( ) );
            daoUtil.setInt( ++nIndex, resource.getIdResource( ) );
            daoUtil.setString( ++nIndex, resource.getResourceType( ) );
            daoUtil.setInt( ++nIndex, resource.getAdminUser( ).getUserId( ) );
            daoUtil.setTimestamp( ++nIndex, resource.getDateAssignment( ) );
            daoUtil.setBoolean( ++nIndex, resource.isActive( ) );

            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) )
            {
                resource.setId( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
    }

    @Override
    public void delete( ResourceUser resource, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_DELETE, Statement.RETURN_GENERATED_KEYS, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, resource.getId( ) );

            daoUtil.executeUpdate( );
        }

    }

    @Override
    public List<ResourceUser> selectResourcesByUser( int userId, String resourceType, Plugin plugin )
    {
        List<ResourceUser> result = new ArrayList<>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_SELECT_RESOURCE_BY_USER, Statement.RETURN_GENERATED_KEYS, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, userId );
            daoUtil.setString( ++nIndex, resourceType );

            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                int i = 0;
                ResourceUser resource = new ResourceUser( );
                resource.setId( daoUtil.getInt( ++i ) );
                resource.setIdResource( daoUtil.getInt( ++i ) );
                resource.setResourceType( daoUtil.getString( ++i ) );
                resource.setAdminUser( AdminUserHome.findByPrimaryKey( daoUtil.getInt( ++i ) ) );
                resource.setDateAssignment( daoUtil.getTimestamp( ++i ) );
                resource.setActive( daoUtil.getBoolean( ++i ) );
                result.add( resource );
            }
        }
        return result;
    }

    @Override
    public List<AdminUser> selectUserListByResource( int resourceID, String resourceType, Plugin plugin )
    {
        List<AdminUser> result = new ArrayList<>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_SELECT_USER_BY_RESOURCE, Statement.RETURN_GENERATED_KEYS, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, resourceID );
            daoUtil.setString( ++nIndex, resourceType );
            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                result.add( AdminUserHome.findByPrimaryKey( daoUtil.getInt( 1 ) ) );
            }
        }

        return result;
    }
    
    @Override
    public List<AdminUser> selectUserListByResourceType( String resourceType, Plugin plugin )
    {
        List<AdminUser> result = new ArrayList<>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_SELECT_USER_BY_RESOURCE_TYPE, Statement.RETURN_GENERATED_KEYS, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setString( ++nIndex, resourceType );
            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                result.add( AdminUserHome.findByPrimaryKey( daoUtil.getInt( 1 ) ) );
            }
        }

        return result;
    }

    @Override
    public void deactivateByUserResource( int userId, int resourceID, String resourceType, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_DEACTIVATE_BY_USER_BY_RESOURCE, Statement.RETURN_GENERATED_KEYS, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, userId );
            daoUtil.setInt( ++nIndex, resourceID );
            daoUtil.setString( ++nIndex, resourceType );

            daoUtil.executeUpdate( );
        }

    }

    @Override
    public void deleteAssignmentByResource( int resourceID, String resourceType, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_DELETE_BY_RESOURCE, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, resourceID );
            daoUtil.setString( ++nIndex, resourceType );
            daoUtil.executeUpdate( );
        }

    }
}
