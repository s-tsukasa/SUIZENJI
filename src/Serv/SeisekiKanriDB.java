package Serv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	//得点リスト作成 seitosyousai testsyousai test
	//学年、学級、テストID、教科IDからリストを返す
	public ArrayList<TokutenTbl> getTokutenTblList(int nen1,String kyu1,int tid1,int kid1) {
		ArrayList<TokutenTbl> list = new ArrayList<TokutenTbl>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT no,namae,seitosyousai.sid,seitosyousai.nen,kyu,tid,kid,ten FROM "
					+ "((seitosyousai LEFT JOIN tokuten on seitosyousai.sid = tokuten.sid) "
					+ "JOIN testsyousai ON tokuten.tdid=testsyousai.tdid)"
					+ "JOIN seito ON seito.sid=seitosyousai.sid"
					+ " WHERE seitosyousai.nen = ? AND  kyu=? AND tid=? AND kid = ? "
					+ "AND seitosyousai.delete_flag=0 ORDER BY ten DESC");

			stmt.setInt(1, nen1);
			stmt.setString(2,kyu1);
			stmt.setInt(3, tid1);
			stmt.setInt(4, kid1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TokutenTbl se = new TokutenTbl();
				se.setSid(rs.getInt("seitosyousai.sid"));
				se.setKyu(rs.getString("kyu"));
				se.setNamae(rs.getString("namae"));
				se.setGakunen(rs.getInt("seitosyousai.nen"));
				se.setNo(rs.getInt("no"));
				se.setTid(rs.getInt("tid"));
				se.setKid(rs.getInt("kid"));
				se.setTen(rs.getInt("ten"));
				list.add(se);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//学年、学級、テストIDからリストを返す。点数はテストの合計点
	public ArrayList<TokutenTbl> getTokutenTblList(int nen1,String kyu1,int tid1) {
		ArrayList<TokutenTbl> list = new ArrayList<TokutenTbl>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT no,namae,seitosyousai.sid,seitosyousai.nen,kyu,tid,kid,sum(ten) FROM "
					+ "((seitosyousai LEFT JOIN tokuten on seitosyousai.sid = tokuten.sid) "
					+ "JOIN testsyousai ON tokuten.tdid=testsyousai.tdid)"
					+ "JOIN seito ON seito.sid=seitosyousai.sid"
					+ " WHERE seitosyousai.nen = ? AND  kyu=? AND tid=? AND seitosyousai.delete_flag=0 "
					+ "group by sid ORDER BY sum(ten) DESC");

			stmt.setInt(1, nen1);
			stmt.setString(2,kyu1);
			stmt.setInt(3, tid1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TokutenTbl se = new TokutenTbl();
				se.setSid(rs.getInt("seitosyousai.sid"));
				se.setKyu(rs.getString("kyu"));
				se.setNamae(rs.getString("namae"));
				se.setGakunen(rs.getInt("seitosyousai.nen"));
				se.setNo(rs.getInt("no"));
				se.setTid(rs.getInt("tid"));
				se.setKid(0);
				se.setTen(rs.getInt("sum(ten)"));
				list.add(se);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//学年、テストID、教科IDからリストを返す、学年全体のリストが帰ってくる
	public ArrayList<TokutenTbl> getTokutenTblList(int nen1,int tid1,int kid1) {
		ArrayList<TokutenTbl> list = new ArrayList<TokutenTbl>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT no,namae,seitosyousai.sid,seitosyousai.nen,kyu,tid,kid,ten FROM "
					+ "((seitosyousai LEFT JOIN tokuten on seitosyousai.sid = tokuten.sid) "
					+ "JOIN testsyousai ON tokuten.tdid=testsyousai.tdid)"
					+ "JOIN seito ON seito.sid=seitosyousai.sid"
					+ " WHERE seitosyousai.nen = ? AND  kid=? AND tid=? AND seitosyousai.delete_flag=0 "
					+ "group by sid ORDER BY ten DESC");

			stmt.setInt(1, nen1);
			stmt.setInt(2, kid1);
			stmt.setInt(3, tid1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TokutenTbl se = new TokutenTbl();
				se.setSid(rs.getInt("seitosyousai.sid"));
				se.setKyu(rs.getString("kyu"));
				se.setNamae(rs.getString("namae"));
				se.setGakunen(rs.getInt("seitosyousai.nen"));
				se.setNo(rs.getInt("no"));
				se.setTid(rs.getInt("tid"));
				se.setKid(rs.getInt("kid"));
				se.setTen(rs.getInt("ten"));
				list.add(se);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//学年、テストID、合計点の学年毎の順位が帰ってくる。
	public ArrayList<TokutenTbl> getTokutenTblList(int nen1,int tid1) {
		ArrayList<TokutenTbl> list = new ArrayList<TokutenTbl>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT no,namae,seitosyousai.sid,seitosyousai.nen,kyu,tid,kid,sum(ten) FROM "
					+ "((seitosyousai LEFT JOIN tokuten on seitosyousai.sid = tokuten.sid) "
					+ "JOIN testsyousai ON tokuten.tdid=testsyousai.tdid)"
					+ "JOIN seito ON seito.sid=seitosyousai.sid"
					+ " WHERE seitosyousai.nen = ? AND  tid=? AND seitosyousai.delete_flag=0 "
					+ "group by sid ORDER BY sum(ten) DESC");

			stmt.setInt(1, nen1);
			stmt.setInt(2, tid1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TokutenTbl se = new TokutenTbl();
				se.setSid(rs.getInt("seitosyousai.sid"));
				se.setKyu(rs.getString("kyu"));
				se.setNamae(rs.getString("namae"));
				se.setGakunen(rs.getInt("seitosyousai.nen"));
				se.setNo(rs.getInt("no"));
				se.setTid(rs.getInt("tid"));
				se.setKid(0);
				se.setTen(rs.getInt("sum(ten)"));
				list.add(se);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//生徒ID、教科IDで生徒の成績一覧を出す。教科ごと
	public ArrayList<TokutenTbl2> getTokutenTbl2List(int sid1,int kid1) {
		ArrayList<TokutenTbl2> list = new ArrayList<TokutenTbl2>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT seito.sid,namae,tnamae,testsyousai.tid,ka,ten FROM "
										+"(((seito LEFT join tokuten on seito.sid=tokuten.sid) "
										+ "JOIN testsyousai ON tokuten.tdid=testsyousai.tdid) "
										+ "JOIN test on testsyousai.tid=test.tid) Join kyouka ON "
										+ "testsyousai.kid=kyouka.kid WHERE seito.sid=? ");
			stmt.setInt(1, sid1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TokutenTbl2 se = new TokutenTbl2();
				se.setSid(rs.getInt("seito.sid"));
				se.setNamae(rs.getString("namae"));
				se.setTnamae(rs.getString("tnamae"));
				se.setTid(rs.getInt("testsyousai.tid"));
				se.setKa(rs.getString("ka"));
				se.setTen(rs.getInt("ten"));
				list.add(se);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//生徒IDで成績一覧をだす。総合点
	public ArrayList<TokutenTbl2> getTokutenTbl2List(int sid1) {
		ArrayList<TokutenTbl2> list = new ArrayList<TokutenTbl2>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT seito.sid,namae,tnamae,testsyousai.tid,kid,sum(ten) FROM "
										+"((seito LEFT join tokuten on seito.sid=tokuten.sid) "
										+ "JOIN testsyousai ON tokuten.tdid=testsyousai.tdid) "
										+ "JOIN test on testsyousai.tid=test.tid WHERE seito.sid=? "
										+ "group by testsyousai.tid ");
			stmt.setInt(1, sid1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TokutenTbl2 se = new TokutenTbl2();
				se.setSid(rs.getInt("seito.sid"));
				se.setNamae(rs.getString("namae"));
				se.setTnamae(rs.getString("tnamae"));
				se.setTid(rs.getInt("testsyousai.tid"));
				se.setKa("合計");
				se.setTen(rs.getInt("sum(ten)"));
				list.add(se);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//---------------------------------------------------------------
	//seito seitosyousai merge
	public ArrayList<SeitoAll> getSeitoAllList() {
		ArrayList<SeitoAll> list = new ArrayList<SeitoAll>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from seito LEFT JOIN seitosyousai ON seito.sid=seitosyousai.sid "
										+ "WHERE seitosyousai.delete_flag=0");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				SeitoAll se = new SeitoAll();
				se.setSid(rs.getInt("sid"));
				se.setNamae(rs.getString("namae"));
				se.setNen(rs.getInt("nen"));
				se.setKyu(rs.getString("kyu"));
				se.setGakunen(rs.getInt("seitosyousai.nen"));
				se.setNo(rs.getInt("no"));
				list.add(se);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Seito 生徒
	public ArrayList<Seito> getSeitoList() {
		ArrayList<Seito> list = new ArrayList<Seito>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from seito WHERE delete_flag=0");
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
	public void insertSeito(String namae, int nen) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql = "insert into seito (namae, nen) values (?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, namae);
			stmt.setInt(2,nen);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateSeito(String namae,int sid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE seito SET namae=? WHERE sid=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, namae);
			stmt.setInt(2,sid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}
	public void deleteSeito(int sid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE seito SET delete_flag=1 WHERE sid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,sid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------
	// SeitoSyousai 生徒詳細
	public ArrayList<SeitoSyousai> getSeitoSyousaiList() {
		ArrayList<SeitoSyousai> list = new ArrayList<SeitoSyousai>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from seitosyousai WHERE delete_flag=0");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				SeitoSyousai sd = new SeitoSyousai();
				sd.setSdid(rs.getInt("sdid"));
				sd.setSid(rs.getInt("sid"));
				sd.setNen(rs.getInt("nen"));
				sd.setKyu(rs.getString("kyu"));
				sd.setNo(rs.getInt("no"));
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
	public void insertSeitoSyousai(int sid,int nen,String kyu,int no) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql = "insert into seitosyousai (sid, nen, kyu, no) values (?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, sid);
			stmt.setInt(2,nen);
			stmt.setString(3,kyu);
			stmt.setInt(4,no);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateSeitoSyousai(int sid,int nen,String kyu,int no) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE seitosyousai SET nen=?,kyu=?,no=? WHERE sid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,nen);
			stmt.setString(2, kyu);
			stmt.setInt(3,no);
			stmt.setInt(4,sid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public void deleteSeitoSyousai(int sid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE seitosyousai SET delete_flag=1 WHERE sid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,sid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
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
	public void insertKyouka(String ka) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql = "insert into kyouka (ka) values (?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,ka);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateKyouka(int kid,String ka) {
		PreparedStatement stmt;
		try {
			//	もとあった教科にdelフラグをつける
			String sql ="UPDATE kyouka SET delete_flag=1 WHERE kid=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,ka);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			//新たな教科を追加する。
			String sql = "insert into kyouka (ka) values (?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,ka);
			//	SQLの実行
			int num = stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteKyouka(int kid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE kyouka SET delete_flag=1 WHERE kid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,kid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
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
	public void insertTest(String tnamae,Timestamp thi,int kikan) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql = "INSERT INTO test (tnamae,thi,kikan) VALUES (?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tnamae);
			stmt.setTimestamp(2,thi);
			stmt.setInt(3,kikan);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateTest(int tid,String tnamae,Timestamp thi,int kikan) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE test SET tnamae=?,thi=?,kikan=? WHERE tid=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,tnamae);
			stmt.setTimestamp(2, thi);
			stmt.setInt(3,kikan);
			stmt.setInt(4,tid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public void deleteTest(int tid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE test SET delete_flag=1 WHERE tid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,tid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------
	// TestSyousai テスト詳細
	//試験IDと強化IDで絞込み
	public ArrayList<TestSyousai> getTestSyousaiList(int tid,int kid) {
		ArrayList<TestSyousai> list = new ArrayList<TestSyousai>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from testsyousai WHERE tid=? AND kid=?");
			stmt.setInt(1,tid);
			stmt.setInt(2,kid);

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
	//単純select
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
	public void insertTestSyousai(int tid,int kid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql = "insert into testsyousai (tid,kid) values (?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,tid);
			stmt.setInt(2,kid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateTestSyousai(int tdid,int kid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE testsyousai SET kid=? WHERE tdid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,tdid);
			stmt.setInt(2,kid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public void deleteTestSyousai(int tdid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE test SET delete_flag=1 WHERE tdid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,tdid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------
	// Tokuten 得点
	//生徒IDと試験詳細IDで絞り込む
	public ArrayList<Tokuten> getTokutenList(int sid,int tdid) {
		ArrayList<Tokuten> list = new ArrayList<Tokuten>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * from tokuten WHERE sid=? AND tdid=?");
			stmt.setInt(1,sid);
			stmt.setInt(2,tdid);
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
	//単純select
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
	public void insertTokuten(int sid,int tdid,int ten) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql = "insert into tokuten (sid,tdid,ten) values (?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,sid);
			stmt.setInt(2,tdid);
			stmt.setInt(3,ten);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateTokuten(int tenid,int ten) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE tokuten SET ten=? WHERE tenid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,ten);
			stmt.setInt(2,tenid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public void deleteTokuten(int tenid) {
		PreparedStatement stmt;
		try {
			//	プリペアードステートメント
			String sql ="UPDATE tokuten SET delete_flag=1 WHERE tenid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,tenid);
			//	SQLの実行
			int num = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
