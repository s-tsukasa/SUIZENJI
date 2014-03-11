package Serv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeisekiKanriDB {
	private Connection con = null; // コネクション初期設定

	public SeisekiKanriDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/seisekikanridb", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//---------------------------------------------------------------
	// Seito 生徒
	public ArrayList<Seito> getSeitoList() {
		ArrayList<Seito> list = new ArrayList<Seito>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from seito");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Seito se = new Seito();
				se.setSid(rs.getInt("sid"));
				se.setNamae(rs.getString("namae"));
				se.setNen(rs.getInt("nen"));
				se.setDelete_flag(rs.getInt("delete_flag"));
				list.add(se);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertSeito(String namae, int nen, int delete_flag) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql = "insert into seito (namae, nen, delete_flag) values (?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, namae);
			stmt.setInt(2,nen);
			stmt.setInt(3,delete_flag);

			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateSeito() {
	}
	public void deleteSeito() {
	}

	//---------------------------------------------------------------
	// SeitoSyousai 生徒詳細
	public ArrayList<SeitoSyousai> getSeitoSyousaiList() {
		ArrayList<SeitoSyousai> list = new ArrayList<SeitoSyousai>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from seitosyousai");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				SeitoSyousai sd = new SeitoSyousai();
				sd.setSdid(rs.getInt("sdid"));
				sd.setSid(rs.getInt("sid"));
				sd.setNen(rs.getInt("nen"));
				sd.setKyu(rs.getString("kyu"));
				sd.setNen(rs.getInt("no"));
				sd.setDelete_flag(rs.getInt("delete_flag"));
				list.add(sd);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertSeitoSyousai() {
	}
	public void updateSeitoSyousai() {
	}
	public void deleteSeitoSyousai() {
	}

	//---------------------------------------------------------------
	// Kyouka 教科
	public ArrayList<Kyouka> getKyoukaList() {
		ArrayList<Kyouka> list = new ArrayList<Kyouka>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from kyouka");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Kyouka ky = new Kyouka();
				ky.setKid(rs.getInt("kid"));
				ky.setKa(rs.getString("ka"));
				ky.setDelete_flag(rs.getInt("delete_flag"));
				list.add(ky);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertKyouka() {
	}
	public void updateKyouka() {
	}
	public void deleteKyouka() {
	}

	//---------------------------------------------------------------
	// Test 試験
	public ArrayList<Test> getTestList() {
		ArrayList<Test> list = new ArrayList<Test>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from test");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Test te = new Test();
				te.setTid(rs.getInt("tid"));
				te.setTnamae(rs.getString("tnamae"));
				te.setThi(rs.getTimestamp("thi"));
				te.setKikan(rs.getInt("kikan"));
				te.setDelete_flag(rs.getInt("delete_flag"));
				list.add(te);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertTest() {
	}
	public void updateTest() {
	}
	public void deleteTest() {
	}

	//---------------------------------------------------------------
	// TestSyousai テスト詳細
	public ArrayList<TestSyousai> getTestSyousaiList() {
		ArrayList<TestSyousai> list = new ArrayList<TestSyousai>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from testsyousai");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TestSyousai ts = new TestSyousai();
				ts.setTdid(rs.getInt("tdid"));
				ts.setTid(rs.getInt("tid"));
				ts.setKid(rs.getInt("kid"));
				ts.setDelete_flag(rs.getInt("delete_flag"));
				list.add(ts);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertTestSyousai() {
	}
	public void updateTestSyousai() {
	}
	public void deleteTestSyousai() {
	}

	//---------------------------------------------------------------
	// Tokuten 得点
	public ArrayList<Tokuten> getTokutenList() {
		ArrayList<Tokuten> list = new ArrayList<Tokuten>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from tokuten");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tokuten to = new Tokuten();
				to.setTenid(rs.getInt("tenid"));
				to.setSid(rs.getInt("sid"));
				to.setTdid(rs.getInt("tdid"));
				to.setTen(rs.getInt("ten"));
				to.setDelete_flag(rs.getInt("delete_flag"));
				list.add(to);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertTokuten() {
	}
	public void updateTokuten() {
	}
	public void deleteTokuten() {
	}
}
