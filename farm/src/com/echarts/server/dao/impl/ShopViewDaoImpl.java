package com.echarts.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.echarts.client.bean.Seed;
import com.echarts.util.Dbutil;

public class ShopViewDaoImpl implements ShopViewDao {

	private static Connection conn;
	// �����������ݿ�Ĺ�����
	static {
		try {
			conn = Dbutil.getConn2();
			conn.setAutoCommit(false);//����Ϊ�ֶ��ύ����
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����û�������ķ���
	 */
	@Override
	public int getMoney(int userID) {
		int userMoney = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from user where user_id = ?");
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			userMoney = rs.getInt("user_money");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userMoney;
	}

	/**
	 * ����ũ���ȼ��ķ���
	 */
	@Override
	public int levelUp(int userID, boolean isWantToBuy) {
		int userLV = 0;
		int userMoney = 0;

		// ��ȡ��ǰũ���ȼ����û��Ľ�Ǯ��
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from user where user_id = ?");
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			userLV = rs.getInt("user_lv");
			userMoney = rs.getInt("user_money");
			conn.commit();//�ֶ��ύ����
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ����ǰũ���ȼ�������������ã��Լ��ȼ�������������������չ
		// ͬʱ�ж��û��Ǵ��ڡ��鿴���׶Σ����ǡ����򡱽׶�
		// ���鿴����������ũ���������Ϣ�������Ƿ�����ũ���Ľ׶�
		// �����򡱣�ȷ���˹���ũ�������Ľ׶�
		if (userLV < 6) {
			int needMoney = userLV * userLV * 1000;// ������������ķ���
			if (isWantToBuy) {
				if (needMoney <= userMoney) {// �ж��û����еĽ���Ƿ��㹻
					userMoney -= needMoney;
					try {
						conn.setAutoCommit(false);//����Ϊ�ֶ��ύ����
						// �������ݿ⣬���û��Ľ�Һ͵ȼ����д���
						PreparedStatement pstmt = conn
								.prepareStatement("update user set user_lv =?,user_money =? where user_id =?");
						pstmt.setInt(1, userLV + 1);
						pstmt.setInt(2, userMoney);
						pstmt.setInt(3, userID);
						pstmt.executeUpdate();
						// �������ݿ⣬���û������������д���
						pstmt = conn.prepareStatement("insert into field value(null,?,?,?,?,null)");
						pstmt.setInt(1, userID);
						pstmt.setInt(2, 1);
						pstmt.setInt(3, 0);
						pstmt.setInt(4, 0);
						switch (userLV) {
						case 1:
						case 2:
						case 3:
						case 4:
							pstmt.executeUpdate();
							pstmt.executeUpdate();
							break;
						case 5:
							pstmt.executeUpdate();
							pstmt.executeUpdate();
							pstmt.executeUpdate();
							break;
						}
						conn.commit();//�ֶ��ύ����
					} catch (SQLException e) {
						try {
							conn.rollback();//SQL������ʱ�ع�����
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					return userMoney;// ����󣬷����û��������
				} else {
					return -2;// ��ʾ������ҹ���
				}
			} else {
				return needMoney;// ���鿴��״̬ʱ��������Ļ��Ѹ�����
			}
		} else {
			return -1;// ��ʾũ���ȼ��ﵽ����
		}
	}

	/**
	 * ��ȡ��ҳ��������б�1~10����¼��
	 */
	@Override
	public List<Seed> getSeedPage(int userID, int page) {
		List<Seed> seedList = new ArrayList<Seed>();// ������ҳ���������Ϣ
		int pageStart = (page - 1) * 10 + 1;// ��ҳ�����ʼ��Ŀ��
		int pageEnd = page * 10;// ��ҳ�����ֹ��Ŀ��
		int i = 1;// ��¼��ǰ��Ŀ��
		try {
			// �������ӱ��������Ŀ��ȡ���ж�Ӧ��ǰҳ������<=10��
			PreparedStatement pstmt = conn.prepareStatement("select * from seed");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i < pageStart) {// С����ʼ��Ŀ������Ŀֱ������
					i++;
					continue;
				} else if (i <= pageEnd) {// ����������Ŀ����Χ����Ŀ���д���
					i++;
					// ��ѯ��ǰ�����ӵ������Ϣ
					Seed seed = new Seed();
					seed.setSeedId(rs.getInt("seed_id"));
					seed.setSeedName(rs.getString("seed_name"));
					seed.setSeedPrice(rs.getInt("seed_price"));
					seed.setSeedIntroduction(rs.getString("seed_introduction"));
					seed.setSeedHarvestNumber(rs.getInt("seed_harvest_number"));
					seed.setSeedHarvestTime(rs.getInt("seed_harvest_time"));
					seed.setCropId(rs.getInt("crop_id"));
					// ��ѯ��ǰ�����Ӷ�Ӧ�ĳ�����
					PreparedStatement pstmt1 = conn.prepareStatement(
							"select * from repository where user_id =? and res_type =2 and res_goods_id=?");
					pstmt1.setInt(1, userID);
					pstmt1.setInt(2, rs.getInt("seed_id"));
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						seed.setSeedNumber(rs1.getInt("res_number"));
					} else {
						seed.setSeedNumber(0);
					}
					// �ѵ�ǰ��������Ŀ���뼯��
					seedList.add(seed);
				}
			}
			conn.commit();//�ֶ��ύ����
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seedList;// ���ػ�õ�������Ŀ
	}

	/**
	 * �������ӵķ���
	 */
	@Override
	public void buySeed(int userID, int seedID, int numberResult, int moneyResult) {
		// numberResultΪ����ɹ�ʱԤ�Ƶĳ�������moneyResultΪ����ɹ�ʱԤ��ʣ���Ǯ
		try {
			// �����û���ʣ����
			PreparedStatement pstmt = conn.prepareStatement("update user set user_money=? where user_id =?");
			pstmt.setInt(1, moneyResult);
			pstmt.setInt(2, userID);
			pstmt.executeUpdate();
			// ������ӵ��û��Ĳֿ���
			// �ж������Ƿ����
			pstmt = conn
					.prepareStatement("select * from repository where user_id =? and res_type = 2 and res_goods_id =?");
			pstmt.setInt(1, userID);
			pstmt.setInt(2, seedID);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {// ������ʱ�����µ���ؼ�¼
				pstmt = conn.prepareStatement("insert into repository value(null,?,?,2,?)");
				pstmt.setInt(1, userID);
				pstmt.setInt(2, seedID);
				pstmt.setInt(3, numberResult);
				pstmt.executeUpdate();
			} else {// ����ʱ������ؼ�¼
				pstmt = conn.prepareStatement(
						"update repository set res_number =? where user_id =? and res_type =2 and res_goods_id =?");
				pstmt.setInt(1, numberResult);
				pstmt.setInt(2, userID);
				pstmt.setInt(3, seedID);
				pstmt.executeUpdate();
			}
			conn.commit();//������ɺ��ֶ��ύ����
		} catch (SQLException e) {
			try {
				conn.rollback();//SQL������ʱ�ع�����
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
