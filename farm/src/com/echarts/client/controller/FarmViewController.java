package com.echarts.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.echarts.client.bean.Field;
import com.echarts.client.bean.Repository;
import com.echarts.client.bean.Seed;
import com.echarts.client.model.FarmModel;
import com.echarts.client.view.FarmView;
import com.echarts.client.view.OtherFarmView;

/**
 * 1���ֿⰴť( new Repository(xxx).init()) 
 * 2���̳ǰ�ť( new Shop(xxx).init()) 
 * 3�����ð�ť( new Config(xxx).init()) 
 * 4*�����Ѱ�ť 
 * 5*�����䰴ť 
 * 
 * 6�����ذ�ť( showmessage )	��ָ��Ч��
 *
 */
public class FarmViewController implements ActionListener{
	private FarmView farmView;
	private int number;
	private int []plantNumber;
	private List<Field> fieldList;
	private List<Seed> seedList;
	private SimpleDateFormat sdf;
	private JButton area;
	
	private OtherFarmView otherFarmView;
	private int userID;
	


	public FarmViewController(FarmView farmView, int number, int[] plantNumber, List<Field> fieldList,
			List<Seed> seedList, JButton area) {
		this.farmView = farmView;
		this.number = number;
		this.plantNumber = plantNumber;
		this.fieldList = fieldList;
		this.seedList = seedList;
		this.area = area;
		sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
	}

	public FarmViewController(OtherFarmView otherFarmView, int number, int[] plantNumber, List<Field> fieldList,
			List<Seed> seedList,int userID) {
		this.otherFarmView = otherFarmView;
		this.number = number;
		this.plantNumber = plantNumber;
		this.fieldList = fieldList;
		this.seedList = seedList;
		this.userID= userID;
		sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Field field = fieldList.get(number);
		
		//ũ��ҳ��
		if (otherFarmView==null) {
			
			if (plantNumber[number]== -1) {
				//δ����
				
				//����ʱ��
				long cstime = field.getFieldPlantingTime().getTime()+ seedList.get(number).getSeedHarvestTime()*60*1000;
				String csdate = sdf.format(new Date(cstime));
				JOptionPane.showConfirmDialog(farmView, "��ֲ��ֲ�ﻹû����\n�������ƣ�"+seedList.get(number).getSeedName()+"\n����ʱ��Ϊ��"+csdate, "tip", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}else if (plantNumber[number]== 0) {
				//δ��ֲ
				JOptionPane.showConfirmDialog(farmView, "������û����ֲ,��ǰ���ֿ�ʹ������", "tip", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}else {
				//�ѳ���
				int hnumber = field.getFiledHarvestNumber();
				String seedName = seedList.get(number).getSeedName();
				int flag = JOptionPane.showConfirmDialog(farmView, "�Ѿ����죬�Ƿ���ȡ\n��ֲ�����ӣ�"+seedName+"\n�ճ�������"+hnumber+"/"+seedList.get(number).getSeedHarvestNumber(), "tip", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				//�ճ�����
				if (flag==0) {
					//����ȷ������״̬�޸�Ϊ0
					try {
						//�޸�ָ�����ص�״̬
						new FarmModel().updateFieldStatus(field,0);
						
						//�жϲֿ����Ƿ���ڴ��� ���������update ���������� insert 
						Repository repository = new Repository(null, field.getUserId(), seedList.get(number).getCropId(), 1, null);
						Repository repos = new FarmModel().queryRepository(repository);
						if (repos == null) {
							//�����ڣ������
							repository.setResNumber(hnumber);
							new FarmModel().insertRepository(repository);
							
						}else {
							//���ڣ����޸ĳ�����
							repos.setResNumber(repos.getResNumber()+hnumber);
							new FarmModel().updateRepository(repos);
						}
						area.setIcon(new ImageIcon("img\\Plants\\Plants_0.png"));
						plantNumber[number]=0;
						
						
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
					
					
				}
			}
		}else{
			//����ũ��ҳ��
			if (plantNumber[number]== -1) {
				//δ����
				//����ʱ��
				long cstime = field.getFieldPlantingTime().getTime()+ seedList.get(number).getSeedHarvestTime()*60*1000;
				String csdate = sdf.format(new Date(cstime));
				JOptionPane.showConfirmDialog(otherFarmView, "��ֲ��ֲ�ﻹû���죬����͵ȡ\n��ֲ�����ӣ�"+seedList.get(number).getSeedName()+"\n����ʱ��Ϊ��"+csdate, "tip", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}else if (plantNumber[number]== 0) {
				//δ��ֲ
				JOptionPane.showConfirmDialog(otherFarmView, "������û����ֲ", "tip", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}else {
				//�ѳ���
				
				int seedHarvestNumber = seedList.get(number).getSeedHarvestNumber();
				
				//�ж��Ƿ��Ѿ�͵ȡ��
				if (field.getFiledHarvestNumber() < seedHarvestNumber) {
					JOptionPane.showConfirmDialog(otherFarmView, "һ�����������ֻ��͵ȡһ��\n ʣ��������"+field.getFiledHarvestNumber()+"/"+seedList.get(number).getSeedHarvestNumber(), "tip", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int hnumber = (int) (field.getFiledHarvestNumber()*(Math.random()*0.5));
				String seedName = seedList.get(number).getSeedName();
				int flag = JOptionPane.showConfirmDialog(otherFarmView, "�Ѿ����죬�Ƿ�͵ȡ\n�������ƣ�"+seedName+"\n�ճ�ʣ��������"+field.getFiledHarvestNumber()+"/"+seedList.get(number).getSeedHarvestNumber(), "tip", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				//�ճ�����
				
				if (flag==0) {
					//����ȷ������͵ȡ����
					try {
						//�޸�ָ�����ص�״̬
//						new FarmModel().updateFieldStatus(field,0);
						
						//�жϲֿ����Ƿ���ڴ��� ���������update ���������� insert 
						Repository repository = new Repository(null, userID, seedList.get(number).getCropId(), 1, null);
						Repository repos = new FarmModel().queryRepository(repository);
						if (repos == null) {
							//�����ڣ������
							repository.setResNumber(hnumber);
							new FarmModel().insertRepository(repository);
							
						}else {
							//���ڣ����޸ĳ�����
							repos.setResNumber(repos.getResNumber()+hnumber);
							new FarmModel().updateRepository(repos);
						}
						
						//�޸�ʣ������
						field.setFiledHarvestNumber(field.getFiledHarvestNumber()-hnumber);
						new FarmModel().updateFieldHarvestNumber(field);
						
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
					
					
				}
			}
		}

	}
	
	
	
}
