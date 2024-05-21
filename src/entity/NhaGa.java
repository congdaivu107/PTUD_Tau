//2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package entity;

public class NhaGa {
	private String maNhaCC;
	private String tenNhaCC;
	public NhaGa(String maNhaCC, String tenNhaCC) {
		super();
		this.maNhaCC = maNhaCC;
		this.tenNhaCC = tenNhaCC;
	}
	
	public NhaGa(String maNhaCC) {
		super();
		this.maNhaCC = maNhaCC;
	}

	public String getMaNhaCC() {
		return maNhaCC;
	}
	public void setMaNhaCC(String maNhaCC) {
		this.maNhaCC = maNhaCC;
	}
	public String getTenNhaCC() {
		return tenNhaCC;
	}
	public void setTenNhaCC(String tenNhaCC) {
		this.tenNhaCC = tenNhaCC;
	}
	@Override
	public String toString() {
		return "NhaCC [maNhaCC=" + maNhaCC + ", tenNhaCC=" + tenNhaCC + "]";
	}
	
	
}
