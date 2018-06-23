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
 * 1、仓库按钮( new Repository(xxx).init()) 
 * 2、商城按钮( new Shop(xxx).init()) 
 * 3、设置按钮( new Config(xxx).init()) 
 * 4*、好友按钮 
 * 5*、信箱按钮 
 * 
 * 6、土地按钮( showmessage )	有指定效果
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
		
		//农场页面
		if (otherFarmView==null) {
			
			if (plantNumber[number]== -1) {
				//未成熟
				
				//成熟时间
				long cstime = field.getFieldPlantingTime().getTime()+ seedList.get(number).getSeedHarvestTime()*60*1000;
				String csdate = sdf.format(new Date(cstime));
				JOptionPane.showConfirmDialog(farmView, "种植的植物还没成熟\n种子名称："+seedList.get(number).getSeedName()+"\n成熟时间为："+csdate, "tip", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}else if (plantNumber[number]== 0) {
				//未种植
				JOptionPane.showConfirmDialog(farmView, "该土地没有种植,请前往仓库使用种子", "tip", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}else {
				//已成熟
				int hnumber = field.getFiledHarvestNumber();
				String seedName = seedList.get(number).getSeedName();
				int flag = JOptionPane.showConfirmDialog(farmView, "已经成熟，是否收取\n种植的种子："+seedName+"\n收成数量："+hnumber+"/"+seedList.get(number).getSeedHarvestNumber(), "tip", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				//收成数量
				if (flag==0) {
					//若按确定，将状态修改为0
					try {
						//修改指定土地的状态
						new FarmModel().updateFieldStatus(field,0);
						
						//判断仓库中是否存在此种 作物，存在则update ；不存在则 insert 
						Repository repository = new Repository(null, field.getUserId(), seedList.get(number).getCropId(), 1, null);
						Repository repos = new FarmModel().queryRepository(repository);
						if (repos == null) {
							//不存在，则插入
							repository.setResNumber(hnumber);
							new FarmModel().insertRepository(repository);
							
						}else {
							//存在，则修改持有数
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
			//其他农场页面
			if (plantNumber[number]== -1) {
				//未成熟
				//成熟时间
				long cstime = field.getFieldPlantingTime().getTime()+ seedList.get(number).getSeedHarvestTime()*60*1000;
				String csdate = sdf.format(new Date(cstime));
				JOptionPane.showConfirmDialog(otherFarmView, "种植的植物还没成熟，不能偷取\n种植的种子："+seedList.get(number).getSeedName()+"\n成熟时间为："+csdate, "tip", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}else if (plantNumber[number]== 0) {
				//未种植
				JOptionPane.showConfirmDialog(otherFarmView, "该土地没有种植", "tip", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}else {
				//已成熟
				
				int seedHarvestNumber = seedList.get(number).getSeedHarvestNumber();
				
				//判断是否已经偷取过
				if (field.getFiledHarvestNumber() < seedHarvestNumber) {
					JOptionPane.showConfirmDialog(otherFarmView, "一个成熟的作物只能偷取一次\n 剩余数量："+field.getFiledHarvestNumber()+"/"+seedList.get(number).getSeedHarvestNumber(), "tip", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int hnumber = (int) (field.getFiledHarvestNumber()*(Math.random()*0.5));
				String seedName = seedList.get(number).getSeedName();
				int flag = JOptionPane.showConfirmDialog(otherFarmView, "已经成熟，是否偷取\n种子名称："+seedName+"\n收成剩余数量："+field.getFiledHarvestNumber()+"/"+seedList.get(number).getSeedHarvestNumber(), "tip", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				//收成数量
				
				if (flag==0) {
					//若按确定，将偷取作物
					try {
						//修改指定土地的状态
//						new FarmModel().updateFieldStatus(field,0);
						
						//判断仓库中是否存在此种 作物，存在则update ；不存在则 insert 
						Repository repository = new Repository(null, userID, seedList.get(number).getCropId(), 1, null);
						Repository repos = new FarmModel().queryRepository(repository);
						if (repos == null) {
							//不存在，则插入
							repository.setResNumber(hnumber);
							new FarmModel().insertRepository(repository);
							
						}else {
							//存在，则修改持有数
							repos.setResNumber(repos.getResNumber()+hnumber);
							new FarmModel().updateRepository(repos);
						}
						
						//修改剩余数量
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
