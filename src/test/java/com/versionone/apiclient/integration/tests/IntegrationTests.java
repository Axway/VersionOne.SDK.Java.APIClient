package com.versionone.apiclient.integration.tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.versionone.Oid;
import com.versionone.apiclient.Asset;
import com.versionone.apiclient.EnvironmentContext;
import com.versionone.apiclient.IAssetType;
import com.versionone.apiclient.IAttributeDefinition;
import com.versionone.apiclient.IMetaModel;
import com.versionone.apiclient.IServices;
import com.versionone.apiclient.V1APIConnector;

public class IntegrationTests {

    private String _v1Url;
    private String _username;
    private String _password;
    private String _dataUrl;
    private String _metaUrl;
    private String _configUrl;

    private V1APIConnector _dataConnector;
    private V1APIConnector _metaConnector;
    private IMetaModel _metaModel;
    private IServices _services;

    @Before
    public void Setup() throws Exception {
        EnvironmentContext environment = new EnvironmentContext();
        _metaModel = environment.getMetaModel();
        _services = environment.getServices();
    }

    @Test  @Ignore
    public void AddNewAssetWithNonExistentScopeContext() throws Exception {
        Oid projectId = Oid.fromToken("Scope:999999999", _metaModel);
        IAssetType assetType = _metaModel.getAssetType("Story");
        Asset newStory = _services.createNew(assetType, projectId);
        IAttributeDefinition nameAttribute = assetType.getAttributeDefinition("Name");
        newStory.setAttributeValue(nameAttribute, "My New Story");
        _services.save(newStory);

        Assert.assertTrue(newStory.getOid().getToken().length() != 0);
        Assert.assertTrue(newStory.getAttribute(assetType.getAttributeDefinition("Scope")).getValue() != null);
        Assert.assertTrue(newStory.getAttribute(nameAttribute).getValue() != null);
    }

    @Test
    public void AddNewAssetWithExistingScopeContext() throws Exception {
        Oid projectId = Oid.fromToken("Scope:0", _metaModel);
        IAssetType assetType = _metaModel.getAssetType("Story");
        Asset newStory = _services.createNew(assetType, projectId);
        IAttributeDefinition nameAttribute = assetType.getAttributeDefinition("Name");
        newStory.setAttributeValue(nameAttribute, "My New Story");
        _services.save(newStory);

        Assert.assertTrue(newStory.getOid().getToken().length() != 0);
        Assert.assertTrue(newStory.getAttribute(assetType.getAttributeDefinition("Scope")).getValue() != null);
        Assert.assertTrue(newStory.getAttribute(nameAttribute).getValue() != null);
    }

}
