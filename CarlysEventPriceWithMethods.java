import javax.swing.JOptionPane;

public class CarlysEventPriceWithMethods {

	public static void main(String args[] )
	{
		//numGuest();
		displayMotto();
		//calc(getGuest());
		
	}
	public static int numGuest()
	{
		String guestInput = JOptionPane.showInputDialog("Enter the Number of Guest for the party:");
		int numberOfGuest = Integer.parseInt(guestInput);
		return numberOfGuest;
	}
	public static void displayMotto()
	{
		int y =1;
		String str = "Carly's makes the food that makes it a party";
		String display = "";
		int len = str.length();
		
		while(y <= 9)
		{
			if(y == 1 || y == 9)
			{
				
				if (y==9)
				{
					display += "\n";
					
				}
				
				for (int i = 0; i <= len + 11; i++)
				{
					display += "*";
				}
			}
			else if (y == 5)
			{
				display += "\n*"+ " " + " " + " " + " " + " "+ str + " " + " " + " " + " " + " " + "*";
			}
			
			else
			{
				display += "\n";
				for(int j = 0; j <= len + 11; j++)
				{
					if (j == 0 || j == len +11)
					{
						display += "*";
					}
					else
					{
						display += " ";
					}
				}			
			}
			y++;
		}
		 System.out.println(display);
		
	}}
