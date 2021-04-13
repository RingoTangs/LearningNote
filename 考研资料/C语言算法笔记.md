**1、编写一个程序，显示指定的文本文件的内容，要求每行中显示的字符数不超过30个字符**。

思路：

1. `FILe* fp = fopen("FileName", `"r/w/..."`)`。
2. 对文件进行操作`fscanf(fp, "%c", &ch) > 0`，可以作为循环条件去读字符。
3. `fclose(fp)`，结束文件操作。

```c
#include <stdio.h>
#include <cstdlib>

/**
* 编写一个程序，显示指定的文本文件的内容，要求每行中显示的字符数不得超过30个字符。
*/
int main(int argc, char const *argv[]) {
	int cnt = 0;
	char ch;

	/**
	* fopen()
	* r： 打开只读;
	* r+：打开读写,从文件头开始;
	* w： 打开只写,如果不存在则创建,如果存在则清空;
	* w+：打开读写,如果不存在则创建,如果存在则清空;
	* a： 打开追加,如果不存在则创建,如果存在则从文件尾部开始;
	* ..x:只新建,如果文件已经存在则不能打开。
	*/
	FILE *fp = fopen("2.1_test.txt", "w+");

	// 1. 判断文件是否存在
	if(fp == NULL) {
		printf("无法打开文件\n");
		exit(0);                             // exit(0)无条件退出程序
	}

	// 2. 写文件
	puts("please input data:");
	for(int i = 1; i <= 10; i++) {
		scanf("%c", &ch);
		fprintf(fp, "%c", ch);               // 写文件
	}

	rewind(fp);                              // 重置指针

	// 3. 读文件
	while(fscanf(fp, "%c", &ch) > 0) {
		cnt++;
		printf("%c", ch);
		if(ch == '\n') {
			cnt = 0;
			continue;
		}
		if(cnt % 30 == 0) printf("\n");
	}
	fclose(fp);                              // 关闭文件

	return 0;
}
```



**2、编写一个函数，将字符串str1中的第 i 个字符到第 j 个字符之间的字符串替换成str2** 。

思路：

1. 在`i-1`处直接加入'\0'将str1分隔开。
2. 计算写数组的长度，动态分配内存空间，注意str1的长度和结尾的'\0'。
3. 新数组str3处结尾的'\0'要加上。

**Case1**：

```c
char* stuffByMe(char *str1, char *str2, int i, int j) {
	int len1 = strlen(str1);
	int len2 = strlen(str2);
	
	// 这里的len3一定是先把str1中不要的去掉后的长度 
	int len3 = len1 - (j - i + 1) + len2;

	char* str3 = (char*)malloc(sizeof(char) * (len3 + 1));
	char* p = str3;
	
	// 巧妙！ 
	str1[i - 1] = '\0';

	while(*str1) *p++ = *str1++;

	while(*str2) *p++ = *str2++;

	str1 += j - i + 1;

	while(*str1) *p++ = *str1++;

	*p = '\0';
	
	return str3;
}
```



**Case2**:

```c
char *stuff(char *str1, char *str2, int i, int j) {
	int len1 = strlen(str1);
	int len2 = strlen(str2);
	int len = len1 - (j - i + 1) + len2;

	char *str3 = (char*)malloc(sizeof(char)*(len + 1));
	str1[i-1] = '\0';
	strcpy(str3, str1);
	strcpy(str3 + i - 1, str2);
	strcpy(str3 + i - 1 + len2, str1 + j);

	return str3;
}
```



**3、找出二维数组的鞍点。鞍点：行最大，列最小。二维数组可能没有鞍点**。

思路：

1. 遍历二维数组找到行最大的元素，记录列的下标。
2. 根据列下标判断是否是二维数组这一列的最小元素，使用flag表示判断鞍点并用计数器计算鞍点个数。
3. 遍历完二维数组，如果计数器个数为0就表示没有鞍点。

```c
#include <stdio.h>
#define ROW 4
#define COL 3

int main(int argc, char const *argv[]) {
	int arr[ROW][COL] = {
		{1, 7, 11},
		{4, 6, 2},
		{7, 8, 9},
		{10, 11, 12}
	};

	int i, j;
	int maxj;                         // 保存最大值点的纵坐标
	int k;
	int cnt = 0;

	for(i = 0; i < ROW; i++) {
		int max = arr[i][0];
		for(j = 0; j < COL; j++) {
			if(arr[i][j] >= max) {
				max = arr[i][j];
				maxj = j;
			}
		}

		int flag = 1;                 // 默认该点为鞍点

		for(k = 0; k < ROW; k++) {
			if(arr[k][maxj] < max) {
				flag = 0;
				break;
			}
		}

		if(flag) {
			printf("a[%d][%d] = %d\t", i, maxj, max);
			cnt++;
		}
	}

	if(cnt == 0) printf("None!\n");

	return 0;
}
```



**4、3 × 3的矩阵，要求将九个不同的数字填入，满足各行，各列，以为各对角线上的三个数之和相等**。

思路：

1. 主要思想：二维数组的遍历。
2. 每行每列都要单独计算和，所以开始新的一行/列都需要给sum初始化。
3. 主对角线和副对角线的和是二维数组遍历结束后完成的，因此只需要初始化一次。

```c
#include <stdio.h>
#define ROW 3
#define COL 3
#define TARGET 15

int isSatisfied(int a[ROW][COL]);

int main(int argc, char const *argv[]) {

	int a[ROW][COL] = {
		{2,9,4},
		{7,5,3},
		{6,1,8},
	};
	
	printf("%d\n", isSatisfied(a));

	return 0;
}

int isSatisfied(int a[ROW][COL]) {
	int sum1 = 0;                 // 行
	int sum2 = 0;                 // 列
	int sum3 = 0;                 // 主对角线
	int sum4 = 0;                 // 副对角线 

	/**
	* 每行都要算出和,因此每行开始时都要将sum1初始化。 
	* 主对角线是遍历完二维数组才计算处的,因此只sum3需要初始化一次即可。 
	*/ 
	for(int i = 0; i < ROW; i++) {
		sum1 = 0;
		for(int j = 0; j < COL; j++) {
			if(i == j) sum3 += a[i][j];
			sum1 += a[i][j];
		}
		if(sum1 != TARGET) return 0;
	}

	if(sum3 != TARGET) return 0;
	
	/**
	* 每列都要计算,所以每列开始时要sum2初始化。计算列用a[j][i]表示。 
	* 计算副对角线用a[i][2-i]来表示。 
	*/ 
	for(int i = 0; i < ROW; i++) {
		sum2 = 0;
		for(int j = 0; j < COL; j++) {
			if(j == COL - 1 - i) sum4 += a[i][j]; 
			sum2 += a[j][i];
		}
		if(sum2 != TARGET) return 0;
	}
	
	if(sum4 != TARGET) return 0;

	return 1;
}
```



**5、韩信点兵：n % 5 == 1; n % 6 == 5; n % 7 == 4; n % 11 == 10; 求解符合这些条件的最小的数**。

```c
#include <stdio.h>
#define MAX 32767

/**
* 5余1、6余5、7余4、11余10，求符合条件最小的数 
*/
int main(int argc, char const *argv[]) {
	int i;
	for(i = 6; i < MAX; i++) {
		if(i % 5 != 1) continue;
		if(i % 6 != 5) continue;
		if(i % 7 != 4) continue;
		if(i % 11 == 10) break;
	}
	
	printf("%d\n", i);                 // 2111
	return 0;
}
```



**6、输入一个字符序列，统计大写、小写字符个数，并将大写字符反序输出(使用指针实现)**。

```c
/**
* 统计大小写字符个数，将大写字母反序输出(用指针操作)
* A-Z：65-90
* a-z：97-122
*/
void func() {
	int n;                                       // 字符串长度
	char ch;                                     // 保存输入的字符

	int cnt1 = 0;                                // 大写字符计数器
	int cnt2 = 0;                                // 小写字符计数器

	printf("请输入要输入字符的个数:\n");
	scanf("%d", &n);

	char* s = (char*)malloc(sizeof(char) * (n + 1));
	char* p = s;

	printf("请输入:\n");

	for(int i = 1; i <= n; i++) {
		ch = getchar();                          // getchar()用来读字符 
		if(ch >= 'A' && ch <= 'Z') cnt1++;
		if(ch >= 'a' && ch <= 'z') cnt2++;
		*p++ = ch;
	}

	printf("大写字母个数：%d\n", cnt1);
	printf("小写字母个数：%d\n", cnt2);

	*p = '\0';
	
	puts(s);
	
	/**
	* 注意：这里是指针不相等
	*/
	while(p != s) {
		if(*p >= 'A' && *p <= 'Z')
			printf("%c\t", *p);
		p--;
	}
}
```



**7、输入某天的年月日，计算为当年的第几天，1998年的9月25日是第268天**。

```c
#include <stdio.h>

/**
* 输入年月日，计算为一年中的哪一天
*/
typedef struct {
	int year;
	int month;
	int day;
} Date;

int main(int argc, char const *argv[]) {
	int months[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	Date date;

	printf("请输入年月日:\n");
	scanf("%d,%d,%d", &date.year, &date.month, &date.day);
	
	if((date.year % 4 == 0 && date.year % 100 != 0) || date.year % 400 == 0)
		months[1] = 29;
	
	int sum = 0;
	
	/**
	* 注意：九月份对应数组的下标是8 
	*/
	for(int i = 0; i < date.month - 1; i++) {
		sum += months[i];
	}
	
	sum += date.day;
	
	printf("%d-%d-%d-%d\n", date.year, date.month, date.day, sum);

	return 0;
}
```



**8、将字符串的第K个字符开始的全部字符复制成为另一个字符串并输出(用指针完成)**。

思路：

- 使用'\0'将字符串有用部分和没用的部分分隔开，会使操作变得十分方便。
- 参考第2题。

```c
void copy(char* s, char* t, int K) {
	if(s == NULL || t == NULL || K < 1) return;
	
	int n1 = strlen(s);
	int n2 = strlen(t);
	int n3 = K - 1 + n2;
	
	char* str = (char*)malloc(sizeof(char) * (n3 + 1));
	char* p = str;
	
	// 重点就是这一步：非常秒 
	s[K-1] = '\0';
	
	while(*s) *p++ = *s++;
	while(*t) *p++ = *t++;
	
	*p = '\0';
	
	puts(str);
}
```



**9、求 s = 1 + 2 × 3 + 4 × 5 × 6 + 7 × 8 × 9 × 10 + 11  ×  12 × 13 × 14 × 15....，求前n项和**。

思路：

1. 可以使用二维数组阶梯存储。
2. 遍历二维数组，每一行求乘积，最后逐行相加。

```C
#include <stdio.h>

int main(int argc, char const *argv[]) {

	int N;
	scanf("%d", &N);
	int a[N][N];
	int number = 1;
	
	// 构造数组 
	for(int i = 0; i < N; i++) {
		for(int j = 0; j <= i; j++) {
			a[i][j] = number;
			number++;
		}
	}
	
	// 求和 
	int sum = 0;
	int multi;
	for(int i = 0; i < N; i++) {
		// 每行都要给multi初始化 
		multi = 1;
		for(int j = 0; j <= i; j++) {
			multi *= a[i][j];
		}
		sum += multi;
	}

	printf("%d\n", sum);
	
	return 0;
}
```



**10、4个评委对6位选手打分（1 ~ 10），统计时要去掉一个最高分和一个最低分，计算出平均分为选手的最后得分，输出得分最高的3位选手的编号和分数**。

```c
#include <stdio.h>
#define N 6
#define M 4

typedef struct {
	int id;
	int score[4];
	double average;
} Person;

int main(int argc, char const *argv[]) {

	Person p[N];

	for(int i = 0; i < N; i++) {
		printf("对%d号选手打分:\n", i+1);
		p[i].id = i + 1;
		for(int j = 0; j < M; j++) {
			scanf("%d", &p[i].score[j]);
		}
	}

	// 要计算每个选手的最大值最小值,计算每个选手时都要初始化 
	int max, min, sum;
	for(int i = 0; i < N; i++) {
		printf("%d号的最高分和最低分：", p[i].id);
		max = 1;
		min = 1;
		sum = 0;
		for(int j = 0; j < M; j++) {
			sum += p[i].score[j];
			if(max >= p[i].score[j]) max = p[i].score[j];
			if(min <= p[i].score[j]) min = p[i].score[j];
		}
		printf("max = %d, min = %d\t", max, min);
		p[i].average = (sum - max - min) * 1.0 / (M - 2);
		printf("平均分：%f\n", p[i].average);
	}
	
	// 冒泡排序(注意从大到小排序)
	Person temp;
	for(int i = 1; i <= N-1; i++) {
		for(int j = 1; j < N; j++) {
			if(p[j - 1].average < p[j].average) {
				temp = p[j];
				p[j] = p[j-1];
				p[j-1] = temp;
			}
		}
	}
	
	printf("得分最高的3位选手为:\n");
	
	for(int i = 0; i < 3; i++) {
		printf("%d号选手,平均分为%f\n", p[i].id, p[i].average);
	}
	
	return 0;
}
```



**11、查找给定子串在主串中首次出现的位置**。

思路：

1. i k 和 j 分别为主串 s 和子串 t 的工作指针，其中 i 是遍历主串的指针。
2. 每次主串和子串开始比较的时候 k j 都要回溯。
3. 如果某次比较中子串 t 直接结束遍历，说明找到了子串在主串中第一次出现的位置。

```c
int position(char* s, char* t) {
	if(s == NULL || t == NULL) return -1;
	
	int i = 0;
	int j = 0;
	int k = 0;
	
	while(s[i]) {
		int k = i;
		int j = 0;
		while(s[k++] == t[j++]) {
			if(t[j] == '\0') return i;
		}
		i++;
	}
	
	return -1;
}
```



**12、查找给定子串在主串中最后一次出现的位置**

```c
int position(char* s, char* t) {
	if(s == NULL || t == NULL) return -1;
	
	int i = 0;
	int j = 0;
	int k = 0;
	
	int index = -1; 
	
	while(s[i]) {
		int k = i;
		int j = 0;
		while(s[k++] == t[j++]) {
			if(t[j] == '\0') index = i;
		}
		i++;
	}
	
	return index;
}
```



**13、判断一个字符串是否是回文数(递归和非递归)**。

```c
// 递归 
int func(char* s, int n) {
	if(s == NULL) return 0;
	if(n == 1) return 1;
	if(*s != *(s + n - 1)) return 0;
	return func(s + 1, n - 2); 
} 

// 非递归 
int func1(char* s, int n) {
	if(s == NULL) return 0;
	if(n == 1) return 1;
	
	char* p = s + n - 1;
	
	while(s != p) {
		if(*s != *p) return 0;
		s++;
		p--;
	}
	
	return 1;
}
```



**14、学生课程成绩数据如下结构体定义**：

```c
struct Student {
    int id;
    float score;
    struct Student* next;
};
```

**(1)建立动态链表存储学生成绩，由键盘输出所有学生的学号，成绩，人数任意，以输入学号0为结束标志**。

**(2)分别统计60以下，60-69，70-79，80-89，90-100各分段的人数，并输出统计结果**。

```c
#include <stdio.h>
#include <stdlib.h>
#define N 5

struct Student {
	int id;
	float score;
	struct Student* next;
};

typedef struct Student Node;

typedef struct {
	Node* head;
} LinkedList;

// ³õÊ¼»¯ 
LinkedList init() {
	LinkedList* list = (LinkedList*)malloc(sizeof(LinkedList));
	list->head = NULL;
	return *list;	
}

// Ìí¼Ó
void add(LinkedList* list, int id, float score) {
	if(list->head == NULL) {
		list->head = (Node*)malloc(sizeof(Node));
		list->head->id = id;
		list->head->score = score;
		list->head->next = NULL;
		return;
	}
	Node* p = (Node*)malloc(sizeof(Node));
	p->id = id;
	p->score = score;
	p->next = list->head->next;
	list->head->next = p;
} 

// ±éÀú
void traverse(Node* x) {
	if(x == NULL) return;
	while(x) {
		printf("Ñ§ºÅ:%d\t ³É¼¨:%f\n", x->id, x->score);
		x = x->next;
	}
} 

int main(int argc, char const *argv[]) {
	
	LinkedList list = init();
	int id;
	float score;
	
	printf("ÇëÊäÈëÑ§Éú³É¼¨ºÍ·ÖÊý(ÓÃ¿Õ¸ñ¸ô¿ª):\n");
	for(int i = 0; i < N; i++) {
		printf("\nÇëÊäÈëµÚ%d¸öÑ§ÉúÐÅÏ¢:\n", i + 1);
		scanf("%d %f", &id, &score);
		add(&list, id, score);
	}
	
	traverse(list.head);
	
	/**
	* a[0]:60ÒÔÏÂ
	* a[1]:60-69
	* a[2]:70-79
	* a[3]:80-89
	* a[4]:90-100 
	*/
	int a[5] = {0};
	Node* p = list.head;
	while(p) {
		if(p->score < 60) a[0]++;
		else if (p->score >= 60 && p->score < 70) a[1]++;
		else if (p->score >= 70 && p->score < 80) a[2]++;
		else if (p->score >= 80 && p->score < 90) a[3]++;
		else a[4]++;
		p = p->next;
	} 
	
	// Í³¼ÆÐÅÏ¢ 
	for(int i = 0; i < 5; i++) {
		printf("%d\t", a[i]);
	}
	
	return 0;
}
```



**15、3 × 3 的二维数组，从键盘输入数据后，将数组中奇数输出到奇数行，偶数输出到偶数行，每行最多3个**。

```c
#include <stdio.h>
#define N 3

int main(int argc, char const *argv[]) {
	int a[N][N] = {0};
	for(int i = 0; i < N; i++) {
		for(int j = 0; j < N; j++) {
			scanf("%d", &a[i][j]);
		}
	}
	int row1 = 0, col1 = 0;                // 奇数行列
	int row2 = 1, col2 = 0;                // 偶数行列
	int b[N][N] = {0};

	for(int i = 0; i < N; i++) {
		for(int j = 0; j < N; j++) {

			// 奇数
			if(a[i][j] % 2 != 0) {
				if(col1 >= N) {
					row1 += 2;
					col1 = 0;
				}
				b[row1][col1++] = a[i][j];
			} else {
				if(col2 >= N) {
					row2 += 2;
					col2 = 0;
				}
				b[row2][col2++] = a[i][j];
			}
		}
	}

	for(int i = 0; i < N; i++) {
		for(int j = 0; j < N; j++) {
			printf("%d\t", b[i][j]);
		}
		printf("\n");
	}

	return 0;
}
```



**16、判断 N 维数组是否是上三角矩阵**。

```c
#include <stdio.h>
#define N 4

// 判断是否符合上三角矩阵
int judge(int a[N][N]) {
	int flag = 1;      // 表示下三角全是0
	
	for(int i = 0; i < N; i++) {
		for(int j = 0; j < i; j++) {
			if(a[i][j] != 0) {
				flag = 0;
				break;
			}
		}
	} 
	 return flag;
} 

int main(int argc, char const *argv[]) {
	int a[N][N] = {
		{1, 2, 3, 4},	
		{0, 5, 6, 9},
		{0, 0, 4, 2},
		{0, 0, 0, 8},
	};
	
	printf("是否是下三角? %d\n", judge(a));
	
	return 0;
}
```



**17、约瑟夫问题**

```c
#include <stdio.h>

/**
* 约瑟夫问题：已知n个人(编号1,2,3...n分别表示)围坐在一张圆桌周围。
* 从编号为1的人开始报数，数到第3个人出列；剩下的人又从1开始报数，
* 数到3的人又出列，一直循环，直到剩下一个人为止。 
*/
int cycle(int people, int num) {
	int i, r = 0;
	for(i = 2; i <= people; i++) r = (r + num) % i;
	return r + 1;
} 


int main(int argc, char const *argv[]) {
	printf("%d\n", cycle(10, 3));
	return 0;
}
```



**18、写一个递归函数，判断字符数组是否是按照字典顺序排列的**。

```c
#include <stdio.h>
#include <string.h>

/**
* 由键盘输入n(n < 50) 个英文单词，每个单词由空格分隔，试编写一个递归函数，
* 判断者n个单词是否按字典排序。
*
* @Param：const char *a[] 字符数组
* @Param：int n           单词个数
* @Return：-1 无单词
*	        0 不符合字典顺序
*           1 符合字典顺序
*
*/
int sorted(const char *a[], int n) {
	if(n <= 0) return -1;                      
	if(n == 1) return 1;                       
	if(strcmp(a[0], a[1]) > 0) return 0;       // 第一个 > 第二个表示不符合字典顺序
	return sorted(++a, --n);
}

int sort(const char *a[], int n) {
	if(n <= 0) return -1;
	if(n == 1) return 1;
	if(strcmp(a[n-2], a[n-1]) > 0) return 0;  // 倒数第二个 > 倒数第一个表示不符合字典顺序 
	return sort(a, n-1); 
}
```



**19、设有A、B、C三个有序的整数链表（递增），删去A链表中那些出现在B链表中又在C链表中出现的结点**。

**注意：删除结点，单链表要有头结点**。

```c
typedef struct _node {
	int data;
	struct _node* next;
} Node;

typedef struct {
	Node* first;
	Node* last;
} LinkedList;

/**
* 判断链表中是否存在该值
* @Return 0 不存在
*         1 存在
*/
int exist(Node* x, int target) {
	if(x == NULL) return 0;
	Node* p;
	for(p = x; p; p = p->next) {
		if(p->data == target) return 1;
	}
}

/**
* 删去A链表中出现在B链表又出现在C链表中的结点
* @Param LinkedList* la A链表
* @Param Node* lb       B链表
* @Param Node* lc       C链表
*/
void dropNode(LinkedList* la, Node* lb, Node* lc) {
	if(la == NULL || la->first == NULL) return ;

	// 给单链表加一个头结点(考试中可以不写,单链要表带有头结点)
	Node* head = (Node*)malloc(sizeof(Node));
	head->next = la->first;
	la->first = head;

	// 正式的代码
	Node* p = la->first;
	Node* q = p->next;
	Node* temp;
	while(q) {

		// 如果该结点在B和C中都存在就删除 否则p 和q 就都向后移
		if(exist(lb, q->data) && exist(lc, q->data)) {
			p->next = q->next;
			temp = q;
			if(temp == la->last) la->last = p;
			q = p->next;
			free(temp);
		} else {
			q = q->next;
			p = p->next;
		}
	}
}
```



**20、将一个数的数码倒过来所得到的新数叫原数的反序数。如果一个数等于它的反序数，则成为它的对陈述。计算不超过1993的最大二进制对称数**。

```c
#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 100


/**
* 计算不超过1993的最大二进制对称数
*/
typedef struct {
	int a[MAXSIZE];
	int length;
} SqList;

/**
* 获得数组：存储二进制
*/
SqList GetBinary(int num) {
	SqList* list = (SqList*)malloc(sizeof(SqList));
	list->length = 0;

	while(num > 0) {
		int remain = num % 2;
		list->a[list->length++] = remain;
		num /= 2;
	}
	return *list;
}

int isTargetNum(int n) {
	SqList list = GetBinary(n);

	int head = 0;
	int tail = list.length - 1;

	int flag = 1;

	// 分奇数和偶数的情况,但是可以结合成一种情况 
	while(head <= tail) {
		if(list.a[head] != list.a[tail]) {
			flag = 0;
			break;
		}
		head++;
		tail--;
	}
	return flag;
}

int main(int argc, char const *argv[]) {
	int i;
	for(i = 1993; i > 0; i--) {
		if(isTargetNum(i)) {
			printf("%d\t", i);
			break;
		}
	}

	return 0;
}
```



**21、编写一个函数，删除在字符串pstr1中出现的所有字符串pstr2**。

```c
#include <stdio.h>

/**
* 字符串src中查找sub第一次出现的位置
*/
char* mystr(char* src, char* sub);

/**
* 计算字符串的长度
*/
int mylen(char* s);

/**
* 字符串拼接
*/
char* mycat(char* dst, char* src);

/**
* 删除字符串s中所有的t
*/
char* delSubStr(char* s, char* t) {
	if(s == NULL || t == NULL) return s;

	// 计算子串的长度
	int n = mylen(t);
	char* p;

	// 找到子串的位置,用剩下的将其拼接即可
	while((p = mystr(s, t)) != NULL) {
		*p = '\0';
		mycat(s, p + n);
	}
	return s;
}

int main(int argc, char const *argv[]) {
	char src[] = "ababcdaberqweabbababa";
	char sub[] = "ab";
	puts(src);
	puts(sub);
	
	char* ret = delSubStr(src, sub);
	puts(ret);
	return 0;
}

/**
* 字符串拼接
*/
char* mycat(char* dst, char* src) {
	int lenOfdst = mylen(dst);

	char *temp = dst;
	dst += lenOfdst;               // dst初始化指向'\0'

	while(*src) *dst++ = *src++;   // while(*src) 等价于 while(*src != '\0')
	*dst = '\0';
	return temp;
}

/**
* 计算字符串的长度
*/
int mylen(char* s) {
	if(s == NULL) return 0;
	int cnt = 0;
	while(*s != '\0') {
		cnt++;
		s++;
	}
	return cnt;
}

/**
* 字符串src中查找sub第一次出现的位置
*/
char* mystr(char* src, char* sub) {

	if(src == NULL || sub == NULL) return src;

	char* p1;                     // 指向src的指针
	char* p2;                     // 指向sub的指针
 
	while(*src != '\0') {
		p1 = src;
		p2 = sub;
		while(*p1++ == *p2++) {
			if(*p2 == '\0') return src;
		}
		src++;
	}
	return NULL;
}
```



**22、小括号匹配问题**。

```c
/**
* 判断字符串"(AB)(CD)()"括号匹配问题
* @Return 0不匹配 1匹配
*/
int isMatched1(char* p, int cnt) {
	if(cnt < 0) return 0;
	if(*p == '\0') return cnt == 0;
	if(*p == '(') return isMatched1(++p, ++cnt);
	if(*p == ')') return isMatched1(++p, --cnt);
	if(*p != '(' || *p != ')') return isMatched1(++p, cnt);
}
```



**23、小括号、中括号、大括号匹配问题**。

```c
/**
* @Param char* s 字符串
* @Param int min 小括号数量
* @Param int mid 中括号数量
* @Param int max 大括号数量
*/
int match(char* s, int min, int mid, int max) {
	if(s == NULL || min < 0 || mid < 0 || max < 0)
		return 0;
	
	if(*s == '\0')
		return min == 0 && mid == 0 && max == 0;
	
	if(*s == '(')
		match(++s, ++min, mid, max);
	else if(*s == '[')
		match(++s, min, ++mid, max);
	else if(*s == '{')
		match(++s, min, mid, ++max);
	else if(*s == ')')
		match(++s, --min, mid, max);
	else if(*s == ']')
		match(++s, min, --mid, max);
	else if(*s == '}')
		match(++s, min, mid, --max);
	else
		match(++s, min, mid, max);
}

int main(int argc, char const *argv[]) {
	char s[100] = "(A){}[B";
	int ret = match(s, 0, 0, 0);
	printf("ret = %d\n", ret);
	return 0;
}
```





**23、设两个有序单链表，一个为升序，另一个为降序。将两个单链表合并**。

```c
/**
* 单链表的反转
*
* @Param Node* first 单链表的头结点
* @Return            单链表反转后新的头结点
*/
Node* reverse(Node* first) {
	if(first == NULL) return NULL;
	Node* p = NULL;
	Node* q = first;
	Node* temp;               // 这个temp是重点 1551 又忘记了 QAQ

	while(q != NULL) {
		temp = q->next;
		q->next = p;
		p = q;
		q = temp;
	}

	return p;
}

/**
* 两个递增单链表合并为一个新的有序单链表
*
* @Param Node* la 递增单链表la的头结点
* @Param Node* lb 递增单链表lb的头结点
* @Return         合并后单链表的头结点
*/
Node* merge(Node* la, Node* lb) {

	// 1.有任意一个单链表为空都返回另一个
	if(la == NULL) return lb;
	if(lb == NULL) return la;

	// 2.为新单链表的头指针赋值
	Node* lc;
	Node *p, *q;
	if(la->val < lb->val) {
		lc = la;
		p = la->next;
	} else if(la->val > lb->val) {
		lc = lb;
		q = lb->next;
	} else {
		lc = la;
		p = la->next;
		q = lb->next;
	}

	// 3.遍历两条单链表的公共部分添加到新的链表
	Node* current = lc;
	current->next = NULL;
	while(p != NULL && q != NULL) {
		if(p->val < q->val) {
			current->next = p;
			current = p;
			p = p->next;
		} else if(p->val > q->val) {
			current->next = q;
			current = q;
			q = q->next;
		} else {
			current->next = p;
			current = p;
			p = p->next;
			q = q->next;
		}
	}

	// 4. 拼接单链表剩余的部分
	if(p != NULL) current->next = p;
	if(q != NULL) current->next = q;

	return lc;
}
```



**24、数组 arr1 和 arr2 按升序排序，将两个数组合并，合并结果存放在数组 arr1 中并且升序**。

```c
#include <stdio.h>
#include <stdlib.h>

/**
* a1和a2是两个升序的数组,将a1和a2合并,合并后的结果存放在数组arr1中且按升序
*
* @Param int a1[] 数组a1
* @Param int n1   数组a1的元素个数
* @Param int a2   数组a2
* @Param int n2   数组a2的元素个数
*/
void merge(int a1[], int n1, int a2[], int n2) {
	// 创建辅助数组
	int* aux = (int*)malloc(sizeof(int) * n1);

	// 将数组a1的元素全部拷贝到辅助数组aux中
	for(int i = 0; i < n1; i++) {
		aux[i] = a1[i];
	}

	int p1 = 0;                   // 指向aux辅助数组
	int p2 = 0;                   // 指向数组a2

	// 归并的过程
	for(int i = 0; i < n1 + n2; i++) {
		if(p1 >= n1) a1[i] = a2[p2++];
		else if(p2 >= n2) a1[i] = aux[p1++];
		else if(aux[p1] < a2[p2]) a1[i] = aux[p1++];
		else a1[i] = a2[p2++];
	}
	free(aux);
}
```



**25、求字符串a和字符串b的最长公共子串**。

```c
#include <stdio.h>
#include <string.h>
#define max(a, b) ((a) > (b) ? (a) : (b))

/**
* 获得最长公共子串的长度并打印最长公共子串 
*/
int getLCS(char* s, char* t) {
	if(s == NULL || t == NULL) return 0;
	
	int slen = strlen(s);                        // 字符串s的长度 
	int tlen = strlen(t);                        // 字符串t的长度
	int a[slen][tlen] = {0};                     // 辅助数组初始化 

	int ret = 0;                                 // 记录公共子串的长度 
	int idx = 0;	                             // 记录下标 
	for(int i = 0; i < slen; i++) {
		for(int j = 0; j < tlen; j++) {
			if(s[i] == t[j]) {
				if(i == 0 || j == 0) 
					a[i][j] = 1;
				else
					a[i][j] = a[i-1][j-1] + 1;
				if(a[i][j] > ret) {
					ret = a[i][j];
					idx = i;
				} 
			}
		}
	} 
	
	// 打印最长公共子串 
	for(int i = idx - ret + 1; i <= idx; i++) {
		printf("%c",s[i]); 
	} 
	
	return ret;
}
```



**26、汉诺塔**。

```c
#include <stdio.h>

/**
* 汉诺塔的递归实现(2^n - 1)
* 
* 思想：借助B将A上编号从1~n的盘子转移到C上 
*  1. 如果只有一个盘子只需要移动一步 
*  2. 将A上的n-1个盘子通过C转移到B的步数 + 第n个盘子转移到C + B上的n-1个盘子通过A转移到C 
*/
int tower(int n, char A, char B, char C) {
	if(n == 1) return 1;
	return tower(n - 1, A, C, B) + 1 + tower(n - 1, B, A, C);
}

int main(int argc, char const *argv[]) {
	int ret = tower(5, 'A', 'B', 'C');
	printf("%d\n", ret); 
	return 0;
}
```

