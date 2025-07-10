// 20215673 scynz1 Ning Zhu
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include<time.h>

//This program handle most of the error cases, but some cases almost impossible are ignored.

struct cities
{
	char city[15];
	char city_code[4];
	char branch[5][15];
	char branch_code[5][3];
};

struct time
{
	int year;
	int month;
	int day;
};

struct transfer
{
	struct time date;
	char d_account[11];
	char c_account[11];
	long long amount;
};

//flag = 0 means close, flag = 1 means open, flag = 2 means transfer.
struct datas
{
	int flag;
	char name[101];
	char id[16];
	char address[201];
	char birth[11];
	long long deposit;
	//Divide the 10 digits to three part makes the whole program easier.
	char city[4];
	char branch[3];
	int number;
	struct time date;
	struct transfer tran;
};

void printMenu();
char *prompt(char *mesg);
//Check that int(option) is an integer between 1 and 9
int isValidOption(char **ptr_to_option);
//Check that int(option) is 10
int isQuitOption(char **ptr_to_option);

//Execute option
void switchOption(char **ptr_to_option);

void optionOne();
//Print all cities
void printCity();
//Print all branches by city_code
void printBranchByCity(char *city_code);

void optionTwo();
//Check the city code
int isCityCodeValid(char *city_code);
//Check the branch code
int isBranchCodeValid(char *branch_code);
//Get current date
struct time getDate();
//Get the number(XXXXX)
int findNumber(char *city_code, char *branch_code);
//Load datas in file
void loadIn(struct datas data);

void optionThree();
//Print the account by account_number
void printAccount(char *account_number);
//Print the details by given structure
void printDetails(struct datas account);

void optionFour();
//Print the account list by city_code and branch_code
void printAccountList(char *city_code, char *branch_code);

void optionFive();
void optionSix();
//Print three details and return deposit if account_number exist, else print error message and return -1
long long printSomeDetails(char *account_number);
//Check wheter the amount is valid(including non-number characters)
int isAmountValid(char *amount);
//Update the file
void changeBalance(char *account_number, long long new_balance);

void optionSeven();
//Record the transfer details
void recordTransfer(struct datas data);

void optionEight();
//Check whether account is in the file
int isExist(char *account);
//Store the account_number in struct
struct time transformation(char *date);
//If start>end,return 1. If start<end,return -1. If start=end,return 0.
int compare(struct time start, struct time end);
//Print the transaction between start and end
void printTransaction(struct time start, struct time end, char *account);
//Check date
int isValidDate(char *date);

void optionNine();
//Close account by account_number
void closeAccount(char *account_number);

int main()
{
	//My valid option does not include 10, use isQuitOption to deal with quit problem.
	while(1)
	{
		printMenu();
		char *option = prompt("Option: ");
		printf("\n");
		char **ptr_to_option = &option;

		if(isQuitOption(ptr_to_option) == 1)
		{
			free(option);
			break;
		}

		while(isValidOption(ptr_to_option) == 0)
		{
			free(option);
			printf("Unknown option.\n\n");
			option = prompt("Option: ");
			printf("\n");
			ptr_to_option = &option;

			if(isQuitOption(ptr_to_option) == 1)
			{
				break;
			}
		}
		
		if(isQuitOption(ptr_to_option) == 1)
		{
			free(option);
			break;
		}
		else
		{
			switchOption(ptr_to_option);
			free(option);
		}
	}
}

void printMenu()
{
	printf("1) Show city code and branch code\n");
	printf("2) Open an account\n");
	printf("3) Show account details\n");
	printf("4) Show list of accounts\n");
	printf("5) Deposit in an account\n");
	printf("6) Withdraw from an account\n");
	printf("7) Transfer money\n");
	printf("8) Transaction details\n");
	printf("9) Close an account\n");
	printf("10)Quit\n");
	printf("\n");
}

char *prompt(char *mesg)
{
	printf("%s",mesg);
	int buffer_size = 1;
	int bidx = 0;
	
	char *buffer = malloc(sizeof(char) * buffer_size);
	if(buffer == NULL)
	{
		printf("Unable to allocate memory.\n");
		exit(-1);
	}
	
	while(1)
	{
		char temp = '\0';
		scanf("%c", &temp);
		
		if(temp == '\n')
		{
			buffer[bidx] = '\0'; 
			break;
		}
		else
		{
			buffer[bidx] = temp;
			bidx++;
			if(bidx == buffer_size)
			{		
				char *newbuf = realloc(buffer, sizeof(char) * (buffer_size+1));
					
				if(newbuf == NULL)
				{
					free(buffer);
					printf("Unable to allocate memory.\n");
					exit(-1);	
				}
				else
				{
					buffer = newbuf;
					buffer_size++;
				}
			}
		}
	}
	return buffer;
}

int isValidOption(char **ptr_to_option)
{
	if(strlen(*ptr_to_option) != 1)
		return 0;
	if(**ptr_to_option != '1' && **ptr_to_option != '2' && **ptr_to_option != '3' && **ptr_to_option != '4' && **ptr_to_option != '5' && **ptr_to_option != '6' && **ptr_to_option != '7' && **ptr_to_option != '8' && **ptr_to_option != '9')
		return 0;
	return 1;
}

int isQuitOption(char **ptr_to_option)
{
	if(strlen(*ptr_to_option) != 2)
		return 0;
	if(**ptr_to_option != '1')
		return 0;
	if(*(*ptr_to_option + 1) != '0')
		return 0;
	return 1;
}

void switchOption(char **ptr_to_option)
{
	switch(**ptr_to_option)
	{
		case '1':
					optionOne();
					break;

		case '2':
					optionTwo();
					break;

		case '3':
					optionThree();
					break;

		case '4':
					optionFour();
					break;

		case '5':
					optionFive();
					break;

		case '6':
					optionSix();
					break;

		case '7':
					optionSeven();
					break;

		case '8':
					optionEight();
					break;

		case '9':
					optionNine();
					break;
	}
}

void optionOne()
{
	int play = 1;
	while(play == 1)
	{
		printCity();
		char *city_code = prompt("Enter the city code: ");
		printf("\n");
		printBranchByCity(city_code);
		printf("\n");
		
		free(city_code);
		//Search more
		while(1)
		{
			char *play_again = prompt("Do you want to see more city and branch codes (Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
	}

}

void printCity()
{
	struct cities city[10];	//Use array instead of 10 structs

	FILE *fp;
	if((fp = fopen("CityBranchCodes_Ning_Zhu_20215673.txt", "rb")) == NULL)
	{
		printf("File open failed\n");
		return;
	}

	//Read all data
	int i = 0;

	for(i=0;i<10;i++)
		fread(&city[i],sizeof(city[0]),1,fp);

	printf("City\t\tcode\t\tCity\t\tcode\t\tCity\t\tcode\n");
	printf("--------------------------------------------------------------------------------------\n");
	printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\t%s\n",city[0].city,city[0].city_code,city[1].city,city[1].city_code,city[2].city,city[2].city_code);
	printf("%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",city[3].city,city[3].city_code,city[4].city,city[4].city_code,city[5].city,city[5].city_code);
	printf("%s\t\t%s\t\t%s\t%s\t\t%s\t%s\n",city[6].city,city[6].city_code,city[7].city,city[7].city_code,city[8].city,city[8].city_code);
	printf("%s\t%s\n\n",city[9].city,city[9].city_code);
	fclose(fp);
}

void printBranchByCity(char *city_code)
{
	FILE *fp;
	if((fp = fopen("CityBranchCodes_Ning_Zhu_20215673.txt","rb")) == NULL)
	{
		printf("File open failed\n");
		return;
	}
	int time = 0;
	struct cities temp;
	memset(&temp,0,sizeof(temp));
	//Here i know the total number of city is 10.
	while(time < 10)
	{
		fread(&temp,sizeof(temp),1,fp);
		time++;

		if(strcmp(city_code,temp.city_code) == 0)
		{
			printf("Branch name\tcode\t\tBranch name\tcode\t\tBranch name\tcode\n");
			printf("--------------------------------------------------------------------------------------\n");
			printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",temp.branch[0],temp.branch_code[0],temp.branch[1],temp.branch_code[1],temp.branch[2],temp.branch_code[2]);
			printf("%s\t\t%s\t\t%s\t\t%s\n",temp.branch[3],temp.branch_code[3],temp.branch[4],temp.branch_code[4]);
			break;
		}
		
		if(time == 10)
			printf("Invalid city code\n");
	}
	fclose(fp);
}

void optionTwo()
{
	int play = 1;
	while(play == 1)
	{
		//Get the valid datas
		char *a_name = prompt("Enter the name of the account holder: ");
		while(strlen(a_name)>100)
		{
			free(a_name);
			printf("The name of account holder is too long!\n");
			a_name = prompt("Enter the name of the account holder: ");
		}

		char *a_id = prompt("Enter the Id number of the account holder: ");
		while(strlen(a_id)>15)
		{
			free(a_id);
			printf("The Id number of account holder is too long!\n");
			a_id = prompt("Enter the Id number of the account holder: ");		
		}

		char *a_address = prompt("Enter the address of the account holder: ");
		while(strlen(a_address)>200)
		{
			free(a_address);
			printf("The address of account holder is too long!\n");
			a_address = prompt("Enter the address of the account holder: ");
		}

		char *a_city_code = prompt("Enter the city code of the account: ");
		while(isCityCodeValid(a_city_code) == 0)
		{
			free(a_city_code);
			printf("Invalid city code, enter again!\n");
			a_city_code = prompt("Enter the city code of the account: ");
		}

		char *a_branch_code = prompt("Enter the branch code of the account: ");
		while(isBranchCodeValid(a_branch_code) == 0)
		{
			free(a_branch_code);
			printf("Invalid branch code, enter again!\n");
			a_branch_code = prompt("Enter the branch code of the account: ");
		}

		char *a_birth = prompt("Enter the DoB of the account holder: ");
		while(isValidDate(a_birth) == 0)
		{
			free(a_birth);
			printf("Invalid date of birth, enter again!\n");
			a_birth = prompt("Enter the DoB of the account holder: ");		
		}

		char *a_deposit = prompt("Enter the initial balance of the account (not less than 1000 RMB): ");
		while(isAmountValid(a_deposit) == -1)
		{
			free(a_deposit);
			printf("Invalid deposit, enter again!\n");
			a_deposit = prompt("Enter the initial balance of the account (not less than 1000 RMB): ");
		}
		while(atoll(a_deposit) < 1000)
		{
			free(a_deposit);
			printf("Initial deposit can’t be less than 1000 RMB\n");
			a_deposit = prompt("Enter the initial balance of the account (not less than 1000 RMB): ");
			while(isAmountValid(a_deposit) == -1)
			{
				free(a_deposit);
				printf("Invalid deposit, enter again!\n");
				a_deposit = prompt("Enter the initial balance of the account (not less than 1000 RMB): ");
			}
		}
	
		int k = findNumber(a_city_code,a_branch_code);

		printf("The account number is: %s%s%05d\n",a_city_code,a_branch_code,k);
		printf("\n");

		struct time now = getDate();

		//Load datas in file
		struct datas temp;
		memset(&temp,0,sizeof(temp));
		strcpy(temp.name,a_name);
		strcpy(temp.id,a_id);
		strcpy(temp.address,a_address);
		strcpy(temp.birth,a_birth);
		temp.deposit = atoll(a_deposit);
		strcpy(temp.city,a_city_code);
		strcpy(temp.branch,a_branch_code);
		temp.number = k;
		temp.date = now;
		temp.flag = 1;

		loadIn(temp);

		//Open more
		while(1)
		{
			char *play_again = prompt("Do you want to open more accounts?(Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
		free(a_name);
		free(a_id);
		free(a_address);
		free(a_city_code);
		free(a_branch_code);
		free(a_birth);
		free(a_deposit);
	}
}

int isCityCodeValid(char *city_code)
{
	if(strcmp(city_code,"001") != 0 && strcmp(city_code,"002") != 0 && strcmp(city_code,"003") != 0 && strcmp(city_code,"004") != 0 && strcmp(city_code,"005") != 0 && strcmp(city_code,"006") != 0 && strcmp(city_code,"007") != 0 && strcmp(city_code,"008") != 0 && strcmp(city_code,"009") != 0 && strcmp(city_code,"010") != 0)
		return 0;
	return 1;
}

int isBranchCodeValid(char *branch_code)
{
	if(strcmp(branch_code,"01") != 0 && strcmp(branch_code,"02") != 0 && strcmp(branch_code,"03") != 0 && strcmp(branch_code,"04") != 0 && strcmp(branch_code,"05") != 0)
		return 0;
	return 1;
}

struct time getDate()
{
	time_t rawtime;
	struct tm * timeinfo;

	time ( &rawtime );
	timeinfo = localtime ( &rawtime );

	struct time now;
	now.year = 1900+timeinfo->tm_year;
	now.month = 1+timeinfo->tm_mon;
	now.day = timeinfo->tm_mday;

	return now;
}

//Find the "last five digits"
int findNumber(char *city_code, char *branch_code)
{
	struct datas temp;
	int max = 1;
	int exist = 0;

	FILE *fp;

	if((fp = fopen("Account_details.txt","rb")) == NULL)
		return 1;
	while(fread(&temp,sizeof(temp),1,fp))
	{
		if(strcmp(city_code,temp.city)==0 && strcmp(branch_code,temp.branch)==0)
		{
			exist = 1;
			if(max < temp.number)
				max = temp.number;
		}
	}
	fclose(fp);
	if(exist == 0)
		return 1;
	else
		return max+1;
}

void loadIn(struct datas data)
{
	FILE *fp;
	if((fp = fopen("Account_details.txt","ab+")) == NULL)
	{
		printf("File open failed\n\n");
		return;
	}
	fwrite(&data,sizeof(data),1,fp);
	fclose(fp);
}

void optionThree()
{
	int play = 1;
	while(play == 1)
	{
		char *account_number = prompt("Enter an account number: ");
		printf("\n");

		printAccount(account_number);

		free(account_number);
		//See more
		while(1)
		{
			char *play_again = prompt("Do you want to see the details of more accounts?(Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
	}
}

void printAccount(char *account_number)
{

	if(strlen(account_number)!=10)
	{
		printf("Invalid account number!\n\n");
		return;
	}
	int i = 0;
	for(i=0;i<10;i++)
	{
		if(*(account_number+i) != '0' &&  *(account_number+i) != '1' && *(account_number+i) != '2' && *(account_number+i) != '3' && *(account_number+i) != '4' && *(account_number+i) != '5' && *(account_number+i) != '6' && *(account_number+i) != '7' && *(account_number+i) != '8' && *(account_number+i) != '9')
		{
			printf("Invalid account number!\n\n");
			return;
		}
	}
	struct datas temp;
	FILE *fp;
	if((fp = fopen("Account_details.txt","rb")) == NULL)
	{
		printf("File does not exist, please open an account\n\n");
		return;
	}
	int exist = 0;
	while(fread(&temp,sizeof(temp),1,fp))
	{
		if(temp.flag == 1 && strncmp(account_number,temp.city,3)==0 && strncmp(account_number+3,temp.branch,2)==0 && atoi(account_number+5)==temp.number)
		{
			exist = 1;
			printDetails(temp);
			break;
		}
	}
	fclose(fp);
	if(exist==0)
		printf("Account number does not exist!\n\n");
}

void printDetails(struct datas account)
{
	printf("Name of the account holder: %s\n",account.name);
	printf("Id number of the account holder: %s\n",account.id);
	printf("Address of the account holder: %s\n",account.address);
	printf("DoB of the account holder: %s\n",account.birth);
	printf("Balance of the account: %lld\n",account.deposit);
	printf("Date of opening: %d/%02d/%02d\n\n",account.date.year,account.date.month,account.date.day);
}

void optionFour()
{
	int play = 1;
	while(play == 1)
	{
		char *city_code = prompt("Enter the city code: ");
		if(isCityCodeValid(city_code) == 1)
		{
			char *branch_code = prompt("Enter the branch code: ");
			if(isBranchCodeValid(branch_code) == 1)
			{
				printAccountList(city_code,branch_code);
				printf("\n");
			}
			else
				printf("Invalid branch code!\n\n");

			free(branch_code);
		}
		else
			printf("Invalid city code!\n\n");

		free(city_code);

		//See more
		while(1)
		{
			char *play_again = prompt("Do you want to see more account lists(Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
	}
}

void printAccountList(char *city_code, char *branch_code)
{
	FILE *fp;
	struct datas temp;
	if((fp = fopen("Account_details.txt","rb")) == NULL)
	{
		printf("\n");
		printf("File does not exist, please open an account\n");
		return;
	}

	printf("\n");
	printf("Account Number		Name		Id Number		Balance\n");
	printf("--------------------------------------------------------------------------------------\n");	

	while(fread(&temp,sizeof(temp),1,fp))
	{
		if(temp.flag == 1 && strcmp(temp.city,city_code) == 0 && strcmp(temp.branch,branch_code) == 0)
			printf("%s%s%05d\t\t%s\t\t%s\t\t%lld\n",temp.city,temp.branch,temp.number,temp.name,temp.id,temp.deposit);
	}
	fclose(fp);
}

void optionFive()
{
	int play = 1;
	while(play == 1)
	{
		char *account_number = prompt("Enter the account number: ");
		printf("\n");
		long long initial_balance = printSomeDetails(account_number);

		if(initial_balance != -1)
		{
			char *deposit_number = prompt("Enter the deposit amount: ");
			while(isAmountValid(deposit_number) == -1)
			{
				free(deposit_number);
				printf("Invalid amount, enter again!\n");
				deposit_number = prompt("Enter the deposit amount: ");
			}
			printf("\n");
			long long new_balance = initial_balance + atoll(deposit_number);
			free(deposit_number);
			printf("New account balance is: %lld\n\n",new_balance);
			changeBalance(account_number, new_balance);
		}
		free(account_number);
		//Deposit another account
		while(1)
		{
			char *play_again = prompt("Do you want to deposit in another account(Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
	}
}

void optionSix()
{
	int play = 1;
	while(play == 1)
	{
		char *account_number = prompt("Enter the account number: ");
		printf("\n");
		long long initial_balance = printSomeDetails(account_number);

		if(initial_balance != -1)
		{
			char *withdrawal_number = prompt("Enter the withdrawal amount: ");

			while(isAmountValid(withdrawal_number) == -1)
			{
				free(withdrawal_number);
				printf("Invalid amount, enter again!\n");
				withdrawal_number = prompt("Enter the withdrawal amount: ");
			}
			while(atoll(withdrawal_number) > initial_balance)
			{
				free(withdrawal_number);
				printf("The withdrawal amount can’t be more than the balance in the account.\n");
				withdrawal_number = prompt("Enter the withdrawal amount: ");
				while(isAmountValid(withdrawal_number) == -1)
				{
					free(withdrawal_number);
					printf("Invalid amount, enter again!\n");
					withdrawal_number = prompt("Enter the withdrawal amount: ");
				}
			}
			printf("\n");
			long long new_balance = initial_balance - atoll(withdrawal_number);
			free(withdrawal_number);
			printf("New account balance is: %lld\n\n",new_balance);
			changeBalance(account_number, new_balance);
		}
		free(account_number);

		//Withdraw another account
		while(1)
		{
			char *play_again = prompt("Do you want to withdraw in another account(Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
	}
}

long long printSomeDetails(char *account_number)
{

	if(strlen(account_number) != 10)
	{
		printf("Account does not valid!\n\n");
		return -1;
	}

	int i = 0;
	for(i=0;i<10;i++)
	{
		if(*(account_number+i) != '0' &&  *(account_number+i) != '1' && *(account_number+i) != '2' && *(account_number+i) != '3' && *(account_number+i) != '4' && *(account_number+i) != '5' && *(account_number+i) != '6' && *(account_number+i) != '7' && *(account_number+i) != '8' && *(account_number+i) != '9')
		{
			printf("Invalid account!\n\n");
			return -1;
		}
	}
	struct datas temp;
	FILE *fp;
	if((fp = fopen("Account_details.txt","rb")) == NULL)
	{
		printf("File does not exist, please open an account\n\n");
		return -1;
	}
	int exist = 0;
	while(fread(&temp,sizeof(temp),1,fp))
	{
		if(temp.flag == 1 && strncmp(account_number,temp.city,3)==0 && strncmp(account_number+3,temp.branch,2)==0 && atoi(account_number+5)==temp.number)
		{
			exist = 1;
			printf("Name of the account holder: %s\n",temp.name);
			printf("Id number of the account holder: %s\n",temp.id);
			printf("Balance of the account: %lld\n\n",temp.deposit);
			break;
		}
	}
	fclose(fp);
	if(exist==0)
	{
		printf("Account does not exist!\n\n");
		return -1;
	}
	return temp.deposit;
}

//001 is regarded as invalid
int isAmountValid(char *amount)
{
	int len = strlen(amount);
	int temp = 0;
	if(len > 1 && *amount == '0')
		return -1;
	for(temp = 0; temp < len; temp++)
	{
		if(*(amount + temp) != '0' && *(amount + temp) != '1' && *(amount + temp) != '2' && *(amount + temp) != '3' && *(amount + temp) != '4' && *(amount + temp) != '5' && *(amount + temp) != '6' && *(amount + temp) != '7' && *(amount + temp) != '8' && *(amount + temp) != '9')
			return -1;
	}
	return 1;
}

void changeBalance(char *account_number, long long new_balance)
{
	struct datas temp;
	FILE *fp_1;
	FILE *fp_2;
	if((fp_1 = fopen("Account_details.txt","rb")) == NULL)
	{
		printf("File open failed\n\n");
		return;
	}
	if((fp_2 = fopen("New.txt","wb")) == NULL)
	{
		printf("File open failed\n\n");
		return;
	}

	while(fread(&temp,sizeof(temp),1,fp_1))
	{
		if(strncmp(account_number,temp.city,3)==0 && strncmp(account_number+3,temp.branch,2)==0 && atoi(account_number+5)==temp.number)
			temp.deposit = new_balance;
		fwrite(&temp,sizeof(temp),1,fp_2);
	}
	fclose(fp_1);
	fclose(fp_2);

	system("mv New.txt Account_details.txt");	//Rename, ignore the situation when rename fails.
}

void optionSeven()
{
	int play = 1;
	while(play == 1)
	{
		char *account_number_1 = prompt("Enter the debiting account number: ");
		printf("\n");
		long long initial_balance_1 = printSomeDetails(account_number_1);
		if(initial_balance_1 != -1)
		{
			char *account_number_2 = prompt("Enter the crediting account number: ");
			printf("\n");
			long long initial_balance_2 = printSomeDetails(account_number_2);

			if(initial_balance_2 != -1)
			{
				char *transfer_number = prompt("Enter the transfer amount: ");
				while(isAmountValid(transfer_number) == -1)
				{
					free(transfer_number);
					printf("Invalid amount, enter agian!\n");
					transfer_number = prompt("Enter the transfer amount: ");
				}
				printf("\n");
				if(atoll(transfer_number) > initial_balance_1)
				{
					free(transfer_number);
					printf("Transfer amount can’t be more than the balance of the debiting account.\n\n");
					transfer_number = prompt("Enter the transfer amount: ");
					while(isAmountValid(transfer_number) == -1)
					{
						free(transfer_number);
						printf("Invalid amount, enter agian!\n");
						transfer_number = prompt("Enter the transfer amount: ");
					}
					printf("\n");
				}
				if(atoll(transfer_number) <= initial_balance_1)
				{
					printf("Transaction successful\n\n");
					long long new_balance_1 = initial_balance_1 - atoll(transfer_number); 
					long long new_balance_2 = initial_balance_2 + atoll(transfer_number);
					changeBalance(account_number_1, new_balance_1);
					changeBalance(account_number_2, new_balance_2);

					printf("Balance of the account %s is: %lld\n",account_number_1,new_balance_1);
					printf("Balance of the account %s is: %lld\n\n",account_number_2,new_balance_2);

					struct time now = getDate();
					struct transfer data;
					memset(&data,0,sizeof(data));
					data.date = now;
					strcpy(data.d_account, account_number_1);
					strcpy(data.c_account, account_number_2);
					data.amount = atoll(transfer_number);

					struct datas temp;
					memset(&temp,0,sizeof(temp));
					temp.flag = 2;
					temp.tran = data;
				
					recordTransfer(temp);
				}
				else
				{
					printf("Transaction unsuccessful\n\n");
				}
				free(transfer_number);
			}
			free(account_number_2);
		}
		free(account_number_1);

		//Do more transfer
		while(1)
		{
			char *play_again = prompt("Do you want to do more transfer(Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
	}
}

void recordTransfer(struct datas data)
{
	FILE *fp;
	if((fp = fopen("Account_details.txt","ab+")) == NULL)
	{
		printf("File open failed\n");
		return;
	}
	fwrite(&data,sizeof(data),1,fp);
	fclose(fp);
}

void optionEight()
{
	int play = 1;
	while(play == 1)
	{
		char *account = prompt("Enter the account number: ");
		if(isExist(account) == 1)
		{
			char *start_date = prompt("Enter the starting date: ");
			if(isValidDate(start_date) == 1)
			{
				struct time start = transformation(start_date);
				char *end_date = prompt("Enter the ending date: ");
				if(isValidDate(end_date) == 1)
				{
					struct time end = transformation(end_date);
					printf("\n");
					int temp = 1;
					while(compare(start,end) == -1)
					{
						free(end_date);
						printf("End date can’t be later than the start date\n\n");
						end_date = prompt("Enter the ending date: ");
						if(isValidDate(end_date) == 1)
						{
							end = transformation(end_date);
							printf("\n");
						}
						else
						{
							printf("\n");
							printf("Date is not valid\n\n");
							temp = 0;
							break;
						}
					}
					if(temp == 1)
					{			
						printTransaction(start,end,account);
						printf("\n");
					}
				}
				else
				{
					printf("\n");
					printf("Date is not valid\n\n");
				}
				free(end_date);
			}
			else
			{
				printf("\n");
				printf("Date is not valid\n\n");
			}
			free(start_date);
		}
		free(account);

		//Check more transaction details
		while(1)
		{
			char *play_again = prompt("Do you want to check more transaction details(Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
	}
}

int isExist(char *account)
{
	struct datas temp;
	FILE *fp;
	if((fp = fopen("Account_details.txt","rb")) == NULL)
	{
		printf("\n");
		printf("File does not exist, please open an account\n\n");
		return 0;
	}

	if(strlen(account) != 10)
	{
		printf("Account does not valid!\n\n");
		fclose(fp);
		return 0;
	}

	int i = 0;
	for(i=0;i<10;i++)
	{
		if(*(account+i) != '0' && *(account+i) != '1' && *(account+i) != '2' && *(account+i) != '3' && *(account+i) != '4' && *(account+i) != '5' && *(account+i) != '6' && *(account+i) != '7' && *(account+i) != '8' && *(account+i) != '9')
		{
			printf("Invalid account!\n\n");
			fclose(fp);
			return 0;
		}
	}

	while(fread(&temp,sizeof(temp),1,fp))
	{
		if(temp.flag == 1 && strncmp(account,temp.city,3) ==0 && strncmp(account+3,temp.branch,2)==0 && atoi(account+5)==temp.number)
		{
			fclose(fp);
			return 1;
		}
	}
	fclose(fp);

	printf("Account does not exist!\n\n");
	return 0;
}

//Divides *date into three part
struct time transformation(char *date)
{
	struct time temp;
	memset(&temp,0,sizeof(temp));

	//Avoid const pointer
	char a[5] = "0000";
	char b[3] = "00";
	char c[3] = "00";
	
	char *ptr_1 = a;
	char *ptr_2 = b;
	char *ptr_3 = c;

	strncpy(ptr_1,date,4);
	strncpy(ptr_2,date+5,2);
	strncpy(ptr_3,date+8,2);

	temp.year = atoi(ptr_1);
	temp.month = atoi(ptr_2);
	temp.day = atoi(ptr_3);

	return temp;
}

//Check most of the cases, but ignore xxxx/02/30 or xxxx/02/31
int isValidDate(char *date)
{
	if(strlen(date) != 10)
		return 0;
	if(*(date+4) != '/' || *(date+7) != '/')
		return 0;
	if(*date != '1'  && *date != '2')
		return 0;
	if(*(date+1) != '0'  && *(date+1) != '1' && *(date+1) != '2' && *(date+1) != '3' && *(date+1) != '4' && *(date+1) != '5' && *(date+1) != '6' && *(date+1) != '7' && *(date+1) != '8' && *(date+1) != '9')
		return 0;
	if(*(date+2) != '0'  && *(date+2) != '1' && *(date+2) != '2' && *(date+2) != '3' && *(date+2) != '4' && *(date+2) != '5' && *(date+2) != '6' && *(date+2) != '7' && *(date+2) != '8' && *(date+2) != '9')
		return 0;
	if(*(date+3) != '0'  && *(date+3) != '1' && *(date+3) != '2' && *(date+3) != '3' && *(date+3) != '4' && *(date+3) != '5' && *(date+3) != '6' && *(date+3) != '7' && *(date+3) != '8' && *(date+3) != '9')
		return 0;
	if(*(date+5) != '0'  && *(date+5) != '1')
		return 0;
	if(*(date+6) != '0'  && *(date+6) != '1' && *(date+6) != '2' && *(date+6) != '3' && *(date+6) != '4' && *(date+6) != '5' && *(date+6) != '6' && *(date+6) != '7' && *(date+6) != '8' && *(date+6) != '9')
		return 0;
	if(*(date+8) != '0'  && *(date+8) != '1' && *(date+8) != '2' && *(date+8) != '3')
		return 0;
	if(*(date+9) != '0'  && *(date+9) != '1' && *(date+9) != '2' && *(date+9) != '3' && *(date+9) != '4' && *(date+9) != '5' && *(date+9) != '6' && *(date+9) != '7' && *(date+9) != '8' && *(date+9) != '9')
		return 0;
	return 1;
}

//Compare the date
int compare(struct time start, struct time end)
{
	if(start.year>end.year)
		return 1;
	else if(start.year<end.year)
		return -1;
	else
	{
		if(start.month>end.month)
			return 1;
		else if(start.month<end.month)
			return -1;
		else 
		{
			if(start.day>end.day)
				return 1;
			else if(start.day<end.day)
				return -1;
			else
				return 0;
		}
	}
}

//Reverse output
void printTransaction(struct time start, struct time end, char *account)
{
	FILE *fp;
	if((fp = fopen("Account_details.txt","rb")) == NULL)
	{
		printf("File does not exist\n");
		return;
	}

	printf("Date		Debiting account		Crediting account		Transfer amount\n");
	printf("------------------------------------------------------------------------------------------------------\n");
	struct datas temp;

	fseek(fp,0L,SEEK_END);
	long int total = ftell(fp);
	long int count;

	for(count=sizeof(temp);count<=total;count=count+sizeof(temp))
	{
		fseek(fp,-count,SEEK_END);
		fread(&temp,sizeof(temp),1,fp);
		if(compare(start,temp.tran.date) != -1 && compare(temp.tran.date,end) != -1 && temp.flag == 2)
		{
			if(strcmp(account,temp.tran.d_account)==0 || strcmp(account,temp.tran.c_account)==0)
				printf("%d/%02d/%02d\t%s\t\t\t%s\t\t\t%lld\n",temp.tran.date.year,temp.tran.date.month,temp.tran.date.day,temp.tran.d_account,temp.tran.c_account,temp.tran.amount);
		}
	}
	fclose(fp);
}

void optionNine()
{
	int play = 1;
	while(play == 1)
	{
		char *account_number = prompt("Enter an account number:");
		printf("\n");
		long long exist = printSomeDetails(account_number);
		if(exist != -1)
		{
			int temp = 0;
			while(1)
			{
				char *close = prompt("Do you want to close the account(Y/N): ");
				printf("\n");
				if(strcmp(close,"Y") == 0)
				{
					free(close);
					temp = 1;
					break;
				}
				else if(strcmp(close,"N") == 0)
				{
					free(close);
					break;
				}
				else
				{
					free(close);
					printf("Invalid, please enter again!\n\n");
				}
			}
			
			if(temp == 1)
			{
				printf("Account successfully closed\n\n");
				closeAccount(account_number);
			}
			else
				printf("Account remains\n\n");
		}

		free(account_number);

		//Close more
		while(1)
		{
			char *play_again = prompt("Do you want to close more account(Y/N): ");
			printf("\n");
			if(strcmp(play_again,"Y") == 0)
			{
				free(play_again);
				break;
			}
			else if(strcmp(play_again,"N") == 0)
			{
				free(play_again);
				play = 0;
				break;
			}
			else
			{
				free(play_again);
				printf("Invalid, please enter again!\n\n");
			}
		}
	}
}

void closeAccount(char *account_number)
{
	struct datas temp;
	FILE *fp_1;
	FILE *fp_2;
	if((fp_1 = fopen("Account_details.txt","rb")) == NULL)
	{
		printf("File open failed\n\n");
		return;
	}
	if((fp_2 = fopen("Temp.txt","wb")) == NULL)
	{
		printf("File open failed\n\n");
		return;
	}

	while(fread(&temp,sizeof(temp),1,fp_1))
	{
		if(strncmp(account_number,temp.city,3) ==0 && strncmp(account_number+3,temp.branch,2)==0 && atoi(account_number+5)==temp.number)
			temp.flag = 0;
		fwrite(&temp,sizeof(temp),1,fp_2);
	}
	fclose(fp_1);
	fclose(fp_2);

	system("mv Temp.txt Account_details.txt");	//Rename, ignore the situation when rename fails.
}

