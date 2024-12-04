package helenocampos.github.io.fitapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;

@Service
public class FeatureFlagService {
    @Value("${feature-flags.novo-imc-form}")
    private boolean newImcForm;

    public boolean isEnabled(String featureName) {
        return featureName.equals("newImcForm") && newImcForm;
    }

    @Autowired
    private LDClient ldClient;

    @Autowired
    private LDContext ldContext;

    public boolean isFeatureEnabled(String featureKey) {
        return ldClient.boolVariation(featureKey, ldContext, false);                                                                    
    }
}