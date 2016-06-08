import java.util.Vector;

import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

//Class used to initialize analytical engine.
public class AnalyticsInitializer {
	// returns a session object for drools.
	public StatefulKnowledgeSession initializer(int devId) {
		String ruleFile = "rule-" + devId + ".drl";
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();

		kbuilder.add(
				ResourceFactory.newClassPathResource(ruleFile, Number.class),
				ResourceType.DRL);

		final org.drools.KnowledgeBase kbase = KnowledgeBaseFactory
				.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
			throw new RuntimeException("Compilation error.\n"
					+ kbuilder.getErrors().toString());
		}

		final StatefulKnowledgeSession ksession = kbase
				.newStatefulKnowledgeSession();

		return ksession;

	}
	
	
	public boolean fireRules(StatefulKnowledgeSession ks,String agenda,Productlist p)
	{
		boolean b=false;
		ks.setGlobal("passed", b);
		ks.insert(p);
		ks.getAgenda().getAgendaGroup(agenda).setFocus();
		ks.fireAllRules();
		return b;
	}

	// Parameters : Session object of rules,agenda group of rules to be applied,
	// vector of product list objects
	// returns a vector of ProductList objects.
	public Vector<Productlist> firePriceRules(
			StatefulKnowledgeSession ksession, String agenda,
			Vector<Productlist> products_list) {
		Vector<Productlist> intermediate = new Vector<Productlist>();
		Vector<Boolean> passed=new Vector<Boolean>();
		ksession.setGlobal("passed", passed);
		ksession.setGlobal("pIntermediate", intermediate);
		
		for (Productlist p : products_list) {
			ksession.insert(p);
			ksession.getAgenda().getAgendaGroup(agenda).setFocus();
			ksession.fireAllRules();
		}
		
		return intermediate;
	}

	// Parameters : Session object of rules,agenda group of rules to be applied,
	// vector of StoreInfo objects
	// returns a vector of StoreInfo objects.
	public Vector<StoreInfo> fireStoreRules(StatefulKnowledgeSession ksession,
			String agenda, Vector<StoreInfo> products_list) {
		Vector<StoreInfo> intermediate = new Vector<StoreInfo>();
		ksession.setGlobal("sIntermediate", intermediate);
		for (StoreInfo p : products_list) {
			ksession.insert(p);
			ksession.getAgenda().getAgendaGroup(agenda).setFocus();
			ksession.fireAllRules();
		}
		return intermediate;
	}

	// This function fires rules for the location objects and
	// gets the intermediate information
	public Vector<LocationDetails> fireTouristRules(
			StatefulKnowledgeSession ksession, String agenda,
			Vector<LocationDetails> touristInfo) {
		Vector<LocationDetails> intermediate = new Vector<LocationDetails>();
		ksession.setGlobal("lIntermediate", intermediate);
		for (LocationDetails l : touristInfo) {
			ksession.insert(l);
			ksession.getAgenda().getAgendaGroup(agenda).setFocus();
			ksession.fireAllRules();
		}
		return intermediate;
	}

	// to close the session.
	public void close(StatefulKnowledgeSession ksession) {
		ksession.dispose();
	}

}
