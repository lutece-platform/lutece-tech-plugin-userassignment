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

import java.util.List;

import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.plugin.Plugin;

/**
 * This interface provides Data Access methods for {@link ResourceUser} objects
 */
public interface IResourceUserDAO
{

    /**
     * Insert new record in base.
     * 
     * @param resource
     */
    void insert( ResourceUser resource, Plugin plugin );

    /**
     * Delete record from base.
     * 
     * @param resource
     */
    void delete( ResourceUser resource, Plugin plugin );

    /**
     * Select all ResourceUser By User.
     * 
     * @param userId
     * @return
     */
    List<ResourceUser> selectResourcesByUser( int userId, String resourceType, Plugin plugin );

    /**
     * Select all Users By resource.
     * 
     * @param userId
     * @return
     */
    List<AdminUser> selectUserListByResource( int resourceID, String resourceType, Plugin plugin );
    
    /**
     * Select all Users By resource type.
     * 
     * @param resourceType
     * @return
     */
	List<AdminUser> selectUserListByResourceType(String resourceType, Plugin plugin);

    /**
     * Deactivate by User and resource
     * 
     * @param userId
     * @param resourceID
     * @param resourceType
     * @param plugin
     */
    void deactivateByUserResource( int userId, int resourceID, String resourceType, Plugin plugin );

    /**
     * Deletes all assignement of a resource
     * 
     * @param resourceID
     * @param resourceType
     * @param plugin
     */
    void deleteAssignmentByResource( int resourceID, String resourceType, Plugin plugin );
}
