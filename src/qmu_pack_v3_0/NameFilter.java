package qmu_pack_v3_0;

class NameFilter {
	protected String filter(String tempName)
	{
		
		tempName = tempName.replace("."," ");
		tempName = tempName.replace("_"," ");
		tempName = tempName.replace("-"," ");
		tempName = tempName.replace("~"," ");
		tempName = tempName.replace("["," ");
		tempName = tempName.replace("]"," ");
		tempName = tempName.replace("("," ");
		tempName = tempName.replace(")"," ");
		tempName = tempName.replace("{"," ");
		tempName = tempName.replace("}"," ");
		tempName = tempName.replace("+"," ");
		tempName = tempName.replace("@"," ");
		
		
		
		tempName = tempName.trim();
		tempName = tempName.replace(" ","+");
		
		for(int i = 0; i < tempName.length()-1; i++)
		{
			if(tempName.charAt(i) == tempName.charAt(i+1) && tempName.charAt(i) == '+')
			{
				tempName = tempName.substring(0,i+1) + tempName.substring(i+2,tempName.length());
				i--;
			}
		}
		
		return tempName;
	}

}
