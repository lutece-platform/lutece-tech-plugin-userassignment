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
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.test.LuteceTestCase;

public class ResourceUserBusinessTest extends LuteceTestCase
{
	@Test
    public void testCRUD( )
    {
        ResourceUser resourceUser = new ResourceUser( );
        resourceUser.setIdResource( 11 );
        resourceUser.setResourceType( "toto" );
        resourceUser.getAdminUser( ).setUserId( 1 );
        resourceUser.setDateAssignment( Timestamp.valueOf( LocalDateTime.now( ).withNano( 0 ) ) );
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
