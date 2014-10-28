package com.versionone.apiclient.integration.tests;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.versionone.Oid;
import com.versionone.apiclient.APIConfiguration;
import com.versionone.apiclient.Asset;
import com.versionone.apiclient.EnvironmentContext;
import com.versionone.apiclient.IAssetType;
import com.versionone.apiclient.IAttributeDefinition;
import com.versionone.apiclient.IMetaModel;
import com.versionone.apiclient.IServices;

@RunWith(Suite.class)
// NOTE: Add classes here to include in integration test run using Maven "verify" goal.
@Suite.SuiteClasses({ AssetTests.class, 
					ConfigurationTests.class, 
					ConnectorTests.class, 
					EnvironmentContextTests.class, 
					FindBuilderTests.class,
					ModelsAndServicesTests.class, 
					ProxyTests.class, 
					QueryTests.class, 
					UrlsTests.class 
	})
public class APIClientSuiteIT {

	private static APIConfiguration _config;
	private static Oid _projectId; 
	

	@BeforeClass
	public static void beforeRun() throws Exception {
		_config = new APIConfiguration();
		createTestSuite();
	}

	public static void createTestSuite() throws Exception {

		EnvironmentContext _context = new EnvironmentContext();

		IMetaModel _metaModel = _context.getMetaModel();
		IServices _services = _context.getServices();

		Oid projectId = Oid.fromToken("Scope:0", _metaModel);
		IAssetType assetType = _metaModel.getAssetType("Scope");
		Asset newAsset = _services.createNew(assetType, projectId);
		IAttributeDefinition nameAttribute = assetType.getAttributeDefinition("Name");
		newAsset.setAttributeValue(nameAttribute, "Java.SDK Integration Tests");
		_services.save(newAsset);
		
		_projectId = newAsset.getOid().getMomentless();
		
		
	}

	public static APIConfiguration getInstanceUrl() {
		return _config;
	}

	public static Oid get_projectId() {
		return _projectId;
	}
	
	
}
