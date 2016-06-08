import java.io.Serializable;
import java.util.Vector;

/* type=1 xml
 * type=2 loc
 * type=3 product_data   
 * type=4 store info
 * type=5 location info
 */
public class Module23communicator  implements Serializable
{
private int request_id;
private int type;
private String str;
private Vector<Productlist> product_data = new Vector<Productlist>();
private Vector<StoreInfo> store_data = new Vector<StoreInfo>();
private Vector<LocationDetails> location_data = new Vector<LocationDetails>();
private int developer_id;
int getDeveloper_id() {
	return developer_id;
}
void setDeveloper_id(int developer_id) {
	this.developer_id = developer_id;
}
int getRequest_id() {
	return request_id;
}
void setRequest_id(int request_id) {
	this.request_id = request_id;
}
int getType() {
	return type;
}
void setType(int type) {
	this.type = type;
}
String getStr() {
	return str;
}
void setStr(String str) {
	this.str = str;
}
Vector<Productlist> getProduct_data() {
	return product_data;
}
void setProduct_data(Vector<Productlist> product_data) {
	this.product_data = product_data;
}
Vector<StoreInfo> getStore_data() {
	return store_data;
}
void setStore_data(Vector<StoreInfo> store_data) {
	this.store_data = store_data;
}
Vector<LocationDetails> getLocation_data() {
	return location_data;
}
void setLocation_data(Vector<LocationDetails> location_data) {
	this.location_data = location_data;
}
}
