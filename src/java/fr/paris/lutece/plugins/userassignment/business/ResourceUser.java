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

import java.sql.Timestamp;

import fr.paris.lutece.portal.business.user.AdminUser;

/**
 * This class represents an user assignment to a resource
 */
public class ResourceUser
{

    private int _id;
    private int _idResource;
    private String _resourceType;
    private AdminUser _adminUser;
    private Timestamp _dateAssignment;
    private boolean _active;

    public ResourceUser( )
    {
        _adminUser = new AdminUser( );
        _adminUser.setUserId( -1 );
    }

    /**
     * @return the _id
     */
    public int getId( )
    {
        return _id;
    }

    /**
     * @param id
     *            the _id to set
     */
    public void setId( int id )
    {
        this._id = id;
    }

    /**
     * @return the _idResource
     */
    public int getIdResource( )
    {
        return _idResource;
    }

    /**
     * @param idResource
     *            the _idResource to set
     */
    public void setIdResource( int idResource )
    {
        this._idResource = idResource;
    }

    /**
     * @return the _resourceType
     */
    public String getResourceType( )
    {
        return _resourceType;
    }

    /**
     * @param resourceType
     *            the _resourceType to set
     */
    public void setResourceType( String resourceType )
    {
        this._resourceType = resourceType;
    }

    /**
     * @return the _adminUser
     */
    public AdminUser getAdminUser( )
    {
        return _adminUser;
    }

    /**
     * @param adminUser
     *            the _adminUser to set
     */
    public void setAdminUser( AdminUser adminUser )
    {
        this._adminUser = adminUser;
    }

    /**
     * @return the _dateAssignmentDate
     */
    public Timestamp getDateAssignment( )
    {
        if ( _dateAssignment == null )
        {
            return null;
        }
        return Timestamp.from( _dateAssignment.toInstant( ) );
    }

    /**
     * @param dateAssignmentDate
     *            the _dateAssignmentDate to set
     */
    public void setDateAssignment( Timestamp dateAssignmentDate )
    {
        if ( dateAssignmentDate == null )
        {
            this._dateAssignment = null;
        }
        else
        {
            this._dateAssignment = Timestamp.from( dateAssignmentDate.toInstant( ) );
        }
    }

    /**
     * @return the _bIsActive
     */
    public boolean isActive( )
    {
        return _active;
    }

    /**
     * @param isActive
     *            the _bIsActive to set
     */
    public void setActive( boolean isActive )
    {
        this._active = isActive;
    }
}
