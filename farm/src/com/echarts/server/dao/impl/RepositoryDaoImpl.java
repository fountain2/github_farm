package com.echarts.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.echarts.client.bean.Repository;
import com.echarts.client.bean.User;
import com.echarts.util.Dbutil;

public class RepositoryDaoImpl implements RepositoryDao {
	public static Connection conn;

	static {
		try {
			conn = Dbutil.getConn2();
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ��ȡ�û���Ϣ�ķ���
	 * @param userID
	 * @return
	 */
	public User checkUser(int userID){
		User user = new User();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("select * from user where user_id = ?");
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			user.setUserName(rs.getString("user_name"));
			user.setUserMoney(rs.getInt("user_money"));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	/**
	 * ��ʾ�ֿ�ķ������������ݿ�ֿ����ȡ��Ϣ��
	 * 
	 * @param userId
	 *            �û�id
	 * @param page
	 *            �鿴��ҳ��
	 * @return ���ض�Ӧ�Ĳֿ��
	 * @throws SQLException
	 */
	public Repository[] showRepository(int userId, int page) throws SQLException {
		Repository[] repositorys = new Repository[10];
		// ��ȡ��Ӧҳ��ֿ����Ϣ
		PreparedStatement pstmt = conn.prepareStatement("select * from repository where user_id = ?");
		PreparedStatement pstmtPrice;
		PreparedStatement pstmtUser;
		pstmt.setInt(1, userId);
		ResultSet rs = pstmt.executeQuery();
		// i���Ʋ鿴��j���Ƶ���ֿ�����
		int i = 0;
		int j = 0;

		System.out.println("dao������");
		while (rs.next() && i < page * 10) {
			// ��ָ��ҳ�濪ʼ
			if (i >= (page - 1) * 10) {
				System.out.println("Dao" + i);
				Repository repository = new Repository();
				repository.setResId(rs.getInt("res_id"));
				repository.setUserId(rs.getInt("user_id"));
				repository.setResGoodsId(rs.getInt("res_goods_id"));
				repository.setResType(rs.getInt("res_type"));
				repository.setResNumber(rs.getInt("res_number"));
				// ��ȡ���ӱ�͹���������
				if (rs.getInt("res_type") == 1) {
					System.out.println("crop");
					pstmtPrice = conn.prepareStatement("select * from crop where crop_id = ?");
					pstmtPrice.setInt(1, repository.getResGoodsId());
					ResultSet rsPrice = pstmtPrice.executeQuery();
					rsPrice.next();
					repository.setResGoodsName(rsPrice.getString("crop_name"));
					System.out.println(rsPrice.getString("crop_name"));
					repository.setResPrice(rsPrice.getInt("crop_price"));
					repository.setResBrief(rsPrice.getString("crop_introduction"));
					System.out.println(rsPrice.getInt("crop_price") + "crop_price");
				} else if (rs.getInt("res_type") == 2) {
					pstmtPrice = conn.prepareStatement("select * from seed where seed_id = ?");
					pstmtPrice.setInt(1, repository.getResGoodsId());
					ResultSet rsPrice = pstmtPrice.executeQuery();
					rsPrice.next();
					repository.setResGoodsName(rsPrice.getString("seed_name"));
					repository.setResPrice((int) (rsPrice.getInt("seed_price") * 0.4));
					repository.setResBrief(rsPrice.getString("seed_introduction"));
					System.out.println(rsPrice.getInt("seed_price") + "seed_price");
				}

				// ��ȡ�û��������
				pstmtUser = conn.prepareStatement("select * from user where user_id = ?");
				pstmtUser.setInt(1, userId);
				ResultSet rsUser = pstmtUser.executeQuery();
				rsUser.next();
				repository.setUserName(rsUser.getString("user_name"));
				repository.setUserMoney(rsUser.getInt("user_money"));
				repositorys[j] = repository;
				System.out.println(rsUser.getInt("user_money") + "user_money");
				j++;
			}
			i++;
			System.out.println("Dao" + i + "   " + j);
		}
		System.out.println("DaoDao");
		conn.commit();
		return repositorys;
	}

	/**
	 * �鿴����ʣ��������
	 * 
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public int checkField(int userID) throws SQLException {
		int fieldRemaining = 0;
		PreparedStatement pstmt = conn.prepareStatement("select * from field where user_id = ?");
		pstmt.setInt(1, userID);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			if (rs.getInt("field_status") == 0) {
				fieldRemaining++;
			}
		}
		conn.commit();
		return fieldRemaining;
	}

	/**
	 * ʹ�����ӵķ���
	 * 
	 * @param userID
	 *            �û���id
	 * @param resID
	 *            �ֿ�id
	 * @param resGoodsID
	 *            ���ӵ�id
	 * @param useNumber
	 *            ʹ�����ӵĸ���
	 * @return
	 * @throws SQLException
	 */
	public boolean useSeed(int userID, int resID, int resGoodsID, int useNumber) throws SQLException {
		try {
			// �������ر�
			PreparedStatement pstmt = conn.prepareStatement("select * from field where user_id = ?");
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			// �������ӱ�
			PreparedStatement pstmtSeed = conn.prepareStatement("select * from seed where seed_id = ?");
			pstmtSeed.setInt(1, resGoodsID);
			ResultSet rsSeed = pstmtSeed.executeQuery();
			rsSeed.next();
			System.out.println("����");
			PreparedStatement pstmtRes = conn.prepareStatement("select * from repository where res_id=?");
			pstmtRes.setInt(1, resID);
			ResultSet rsRes = pstmtRes.executeQuery();
			rsRes.next();
			if ((rsRes.getInt("res_number") - useNumber) > 0) {
				System.out.println("������");
				PreparedStatement pstmtResChange = conn
						.prepareStatement("update repository set res_number=? where res_id=?");
				pstmtResChange.setInt(1, rsRes.getInt("res_number") - useNumber);
				pstmtResChange.setInt(2, resID);
				pstmtResChange.executeUpdate();
			} else if ((rsRes.getInt("res_number") - useNumber) == 0) {
				System.out.println("ɾ����");
				PreparedStatement pstmtResChange = conn.prepareStatement("delete from repository where res_id = ?");
				pstmtResChange.setInt(1, resID);
				pstmtResChange.executeUpdate();

			}

			int harvestNumber = rsSeed.getInt("seed_harvest_number");
			System.out.println("����" + harvestNumber);
			while (rs.next() && useNumber > 0) {
				if (rs.getInt("field_status") == 0) {
					System.out.println("����");
					PreparedStatement pstmtChange = conn.prepareStatement(
							"update field set seed_id=?,field_status=-1,filed_harvest_number = ?,field_planting_time=null where field_id=?");
					pstmtChange.setInt(1, resGoodsID);
					pstmtChange.setInt(2, harvestNumber);
					pstmtChange.setInt(3, rs.getInt("field_id"));
					pstmtChange.executeUpdate();
					useNumber--;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("sql����");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * �۳����﷽��
	 * @param userID �û���id
	 * @param resID �ֿ��id
	 * @param sellNumber ���۵Ļ�����
	 * @param resPrice ����ĳ��ۼ۸�
	 * @return
	 */
	public int sellGoods(int userID, int resID, int sellNumber, int resPrice) {
		int sellMoney = sellNumber * resPrice;
		try {
			// ���Ӳֿ��
			PreparedStatement pstmt = conn.prepareStatement("select * from repository where res_id = ?");
			pstmt.setInt(1, resID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			// �����û�����ȡ�û�Ŀǰ�Ľ����
			PreparedStatement pstmtUserMoney = conn.prepareStatement("select * from user where user_id = ?");
			pstmtUserMoney.setInt(1, userID);
			ResultSet rsUserMoney = pstmtUserMoney.executeQuery();
			rsUserMoney.next();
			int userMoney = rsUserMoney.getInt("user_money");
			// �����û����޸��û��Ľ����
			PreparedStatement pstmtUser = conn.prepareStatement("update user set user_money=? where user_id = ?");
			pstmtUser.setInt(1, sellMoney + userMoney);
			pstmtUser.setInt(2, userID);
			pstmtUser.executeUpdate();

			// �ж���û������
			if (rs.getInt("res_number") - sellNumber == 0) {
				// �����ˣ�ɾ����Ӧ�Ĳֿ����
				PreparedStatement pstmtSell = conn.prepareStatement("delete from repository where res_id = ?");
				pstmtSell.setInt(1, resID);
				pstmtSell.executeUpdate();
			} else {
				// û���꣬���¶�Ӧ�Ĳֿ����
				PreparedStatement pstmtSell = conn
						.prepareStatement("update repository set res_number=? where res_id = ?");
				pstmtSell.setInt(1, rs.getInt("res_number") - sellNumber);
				pstmtSell.setInt(2, resID);
				pstmtSell.executeUpdate();
			}
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
				return -1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return sellMoney;
	}
}
