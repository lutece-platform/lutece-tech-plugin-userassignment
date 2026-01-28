package fr.paris.lutece.plugins.userassignment.business;

import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.util.RemovalListener;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Locale;

public class UserAssignmentRemovalListener implements RemovalListener
{
    private static final String PROPERTY_USER_CANNOT_BE_REMOVED = "userassignment.message.userCannotBeRemoved";

    @Override
    public boolean canBeRemoved( String strId )
    {
        if ( strId == null )
        {
            return true;
        }

        int nIdUser = Integer.parseInt( strId );

        List<ResourceUser> listResourceUserList = ResourceUserHome.findByUser( nIdUser );

        return CollectionUtils.isEmpty( listResourceUserList );
    }

    @Override
    public String getRemovalRefusedMessage( String s, Locale locale )
    {
        return I18nService.getLocalizedString( PROPERTY_USER_CANNOT_BE_REMOVED, locale );
    }
}
