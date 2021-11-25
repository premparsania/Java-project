//importing sql package
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
class Main
{
	public static void cls()
	{
        try
        {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

	public static void main(String[] args)
	{
		Date d = new Date();
		//defining variables
		//general variable
		String uname;
		String passwd;
		String query;
		Scanner sc = new Scanner(System.in);
		String nul;
		int option;
		int empid;
		String jobtype;
		int mday;
		int aday;
		int lday;
		int ot;
		int sal;


		//getting username and password
		System.out.print("Enter Username : ");
		uname = sc.nextLine();

		System.out.print("Enter Password : ");
		passwd = sc.nextLine();

		try
		{
			//verifying username and password
			conn cn = new conn();
			ResultSet rs = cn.s.executeQuery("select * from login where uname ='"+uname+"' and pass ='"+passwd+"' ;");
			if(rs.next())
			{
				empid = rs.getInt(3);
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String s1 = sf.format(d);
				CallableStatement stmt=cn.c.prepareCall("{call change_date(?,?)}");
				stmt.setInt(1,empid);
				stmt.setString(2,s1);
				stmt.execute();
				do
				{
					System.out.println("||==============================================================================");
					System.out.println("||=> 1.Full Time");
					System.out.println("||=> 2.Half Time");
					System.out.println("||=> 3.Take Leave");
					System.out.println("||=> 0.Exit");
					System.out.println("||==============================================================================");
					System.out.print("Enter A Choice : ");
					option = sc.nextInt();

					switch(option)
					{
						case 1:						
						
						rs = cn.s.executeQuery("select * from emp where empid = "+empid+" and jobType= 'fulltime' ");
						if(rs.next())
						{
							System.out.println("=============================== | Full Time | ==================================");
							System.out.println("Your Employee ID : "+rs.getInt(1));
							System.out.println("Your Name : "+rs.getString(2));
							System.out.println("Job Type : "+rs.getString(3));
							mday = rs.getInt(4);
							System.out.println("Total Hours Of Month : "+(mday-4)+" * 8 = "+((mday-4)*8));
							aday = rs.getInt(5);
							lday = rs.getInt(6);
							System.out.println("Total Hours You Attended : "+(aday-lday)+" * 8 = "+((aday-lday)*8));
							System.out.println("Your Salary : "+(((aday-lday)*8)*40));
							System.out.println("================================================================================");
							System.out.println("=============================== | Over Time | ==================================");
							ot = rs.getInt(7);
							System.out.println("Over time Hours : "+ot);
							System.out.println("Payment per hour : 50");
							System.out.println("Total of Overtime Payment : "+(ot*50));
							System.out.println("================================================================================");
							System.out.println("=============================== | On Leave | ===================================");
							System.out.println("Total Leave : "+lday);
							System.out.println("Date \t\t|\tReason ");
							System.out.println("-------------------------------------------------------------------------------");
							//conn cn1 = new conn();
							rs = cn.s.executeQuery("select * from employeeleave where empid ="+empid+";");
							while(rs.next())
							{
								System.out.println(rs.getString(2)+"\t|\t"+rs.getString(3));
							}
							System.out.println("================================================================================");
							System.out.print("Enter 1 For Salary Slip : ");
							option = sc.nextInt();
							if(option == 1)
							{
								rs = cn.s.executeQuery("select * from emp where empid = "+empid+" and jobType= 'fulltime' ");
								System.out.println("============================= | Salary Slip | ==================================");
								while(rs.next())
								{	
									System.out.println("Your Employee ID : "+rs.getInt(1));
									System.out.println("Your Name : "+rs.getString(2));
									System.out.println("Job Type : "+rs.getString(3));
									sal = (((rs.getInt(5)-rs.getInt(6))*8)*40);
									System.out.println("Salary : "+sal);
									System.out.println("PF : "+((sal*8.5)/100));
									System.out.println("OT : "+(ot*50));
									System.out.println("Gross Salary : "+(((sal+(ot*50))-(sal*8.5)/100)));
								}
							}
						}
						else
						{
							System.out.println("================================================================================");
							System.out.println("Invalid Job Type.....");
							System.out.println("================================================================================");
						}
						break;

						case 2:						
						rs = cn.s.executeQuery("select * from emp where empid = "+empid+" and jobType= 'halftime' ");
						if(rs.next())
						{
							System.out.println("=============================== | Half Time | ==================================");
							System.out.println("Your Employee ID : "+rs.getInt(1));
							System.out.println("Your Name : "+rs.getString(2));
							System.out.println("Job Type : "+rs.getString(3));
							mday = rs.getInt(4);
							System.out.println("Total Hours Of Month : "+(mday-4)+" * 5 = "+((mday-4)*5));
							aday = rs.getInt(5);
							lday = rs.getInt(6);
							System.out.println("Total Hours You Attended : "+(aday-lday)+" * 5 = "+((aday-lday)*5));
							System.out.println("Your Salary : "+(((aday-lday)*5)*40));
							System.out.println("================================================================================");
							System.out.println("=============================== | Over Time | ==================================");
							ot = rs.getInt(7);
							System.out.println("Over time Hours : "+ot);
							System.out.println("Payment per hour : 50");
							System.out.println("Total of Overtime Payment : "+(ot*50));
							System.out.println("================================================================================");
							System.out.println("=============================== | On Leave | ===================================");
							System.out.println("Total Leave : "+lday);
							System.out.println("Date \t\t|\tReason ");
							System.out.println("-------------------------------------------------------------------------------");
							//conn cn1 = new conn();
							rs = cn.s.executeQuery("select * from employeeleave where empid ="+empid+";");
							while(rs.next())
							{
								System.out.println(rs.getString(2)+"\t|\t"+rs.getString(3));
							}
							System.out.println("================================================================================");
							System.out.println("================================================================================");
							System.out.print("Enter 1 For Salary Slip : ");
							option = sc.nextInt();
							if(option == 1)
							{
								rs = cn.s.executeQuery("select * from emp where empid = "+empid+" and jobType= 'halftime' ");
								System.out.println("============================= | Salary Slip | ==================================");
								while(rs.next())
								{	
									System.out.println("Your Employee ID : "+rs.getInt(1));
									System.out.println("Your Name : "+rs.getString(2));
									System.out.println("Job Type : "+rs.getString(3));
									sal = (((rs.getInt(5)-rs.getInt(6))*5)*40);
									System.out.println("Salary : "+sal);
									System.out.println("PF : "+((sal*8.5)/100));
									System.out.println("OT : "+(ot*50));
									System.out.println("Gross Salary : "+(((sal+(ot*50))-(sal*8.5)/100)));
								}
							}
						}
						else
						{
							System.out.println("================================================================================");
							System.out.println("Invalid Job Type.....");
							System.out.println("================================================================================");
						}
						break;

						case 3:
							System.out.println("============================= | Take Leave | ==================================");
							System.out.println("You Have To Take Leave Before 2 Days...");
							System.out.print("Date For Leave (YYYY-MM-DD) : ");
							nul = sc.nextLine();
							String ldate = sc.nextLine();
							System.out.print("Reason for Leave : ");
							String lreason = sc.nextLine();
							query = "insert into employeeleave values("+empid+",'"+ldate+"','"+lreason+"');";
							int row = cn.s.executeUpdate(query);
							System.out.println(row+" Leave Granted");

							query = "update emp set absentdays = (absentdays+1) where empid= "+empid+"";
							row = cn.s.executeUpdate(query);
							System.out.println(row+" Leave Updated");
						break;
						case 0:
							break;

						default:
							System.out.println("Invalid Choice....");
						break;

					}
				}
				while(option != 0);
			}
			else
			{
				System.out.println("Invalid User.....");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}