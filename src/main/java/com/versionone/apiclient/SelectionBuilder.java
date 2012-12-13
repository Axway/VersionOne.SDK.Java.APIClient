package com.versionone.apiclient;

import org.apache.http.message.BasicNameValuePair;

public class SelectionBuilder extends QueryBuilder {
    @Override
    protected void doBuild(Query query, BuildResult result) {
        if (query.getParentRelation() != null) {
            query.getSelection().add(query.getParentRelation());
        }

        if (query.getSelection().size() == 1 && !query.getOid().isNull()) {
            result.pathParts.add(query.getSelection().get(0).getName());
        } else if (query.getSelection().size() > 0) {
            result.querystringParts.add(new BasicNameValuePair("sel", query.getSelection().getToken()));
        } else {
        	result.querystringParts.add(new BasicNameValuePair("sel", ""));
        }
    }
}
