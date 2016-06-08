import java.util.Vector;

import org.drools.runtime.StatefulKnowledgeSession;

// This is the class that has flow of execution for the developer id-1
// => doOperations function has all the info for performing operations.
public class PriceDeveloper {

	GeneratePriceRules g = new GeneratePriceRules();
	PriceHelper p = new PriceHelper();

	Vector<Productlist> product;
	Vector<Vector<StoreInfo>> store;
	public int getMin()
	{
		return this.p.getStart_price();
	}
	public int getMax()
	{
		return this.p.getEnd_price();
	}
	public PriceDeveloper() {
		g = new GeneratePriceRules();
		p = new PriceHelper();
	}
	

	public String getAction() {
		return this.p.getAction();
	}

	public void parse(String fName) {
		this.p = g.getObject(fName);
	}

	public String getQuery() {
		return this.p.getProductName();
	}

	public String getLoc() {
		return this.p.getLoc();
	}

	public String getCity() {
		return this.p.getCity();
	}

	GeneratePriceRules getG() {
		return g;
	}

	void setG(GeneratePriceRules g) {
		this.g = g;
	}

	PriceHelper getP() {
		return p;
	}

	void setP(PriceHelper p) {
		this.p = p;
	}

	Vector<Productlist> getProduct() {
		return product;
	}

	void setProduct(Vector<Productlist> product) {
		this.product = product;
	}

	Vector<Vector<StoreInfo>> getStore() {
		return store;
	}

	void setStore(Vector<Vector<StoreInfo>> store) {
		this.store = store;
	}

	public void finalOperations() {

	}
	
	public int getSentient()
	{
		return this.p.getSentiment();
	}

	public Vector<Productlist> applyPriceRules(Vector<Productlist> products_list) {
		// Setting the range of the project.
		for (Productlist pr : products_list) {
			pr.setStart_price(p.getStart_price());
			pr.setEnd_price(p.getEnd_price());
			pr.setCutOff(p.getSentiment());
		}
		AnalyticsInitializer ob = new AnalyticsInitializer();
		StatefulKnowledgeSession ks = ob.initializer(1); // 1 : parameter
															// developer id
		products_list = new Vector<Productlist>(ob.firePriceRules(ks, "price",
				products_list));
		ob.close(ks);
		return products_list;
	}

	public Vector<Productlist> applyVeoozRules(Vector<Productlist> products_list) {
		for (Productlist pr : products_list) {
			pr.setStart_price(p.getStart_price());
			pr.setEnd_price(p.getEnd_price());
			pr.setCutOff(p.getSentiment());
		}

		AnalyticsInitializer ob = new AnalyticsInitializer();
		StatefulKnowledgeSession ks = ob.initializer(1); // 1 : parameter
															// developer id
		products_list = new Vector<Productlist>(ob.firePriceRules(ks, "veooz",
				products_list));
		ob.close(ks);

		return products_list;
	}

	public Vector<Vector<StoreInfo>> applyStoreRules(
			Vector<Vector<StoreInfo>> stores_list) {
		Vector<Vector<StoreInfo>> temp = new Vector<Vector<StoreInfo>>();
		// Applying rules on each and every product-brand.
		for (int i = 0; i < stores_list.size(); i++) {

			for (StoreInfo s : stores_list.get(i))
				s.setCutOff(p.getRating());

			AnalyticsInitializer ob = new AnalyticsInitializer();
			StatefulKnowledgeSession ks = ob.initializer(1);
			temp.add(ob.fireStoreRules(ks, "store", stores_list.get(i)));
			ob.close(ks);
		}
		return temp;
	}
}