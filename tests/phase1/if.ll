; source file: if.t
; T version: 1.0
; compiled: Sat Jun 01 20:44:28 EDT 2019

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)

; VMTs
@arrayVMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@intVMT = global [1 x i8*] [i8* null]
@Object$VMT = global [1 x i8*] [i8* null]

; class object types
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %xPtr = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %xPtr
  ; load value from variable
  %temp0 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp1 = icmp ne i32 %temp0, 0
  br i1 %temp1, label %ifLabel0, label %ifLabel1
  ; THEN branch
  ifLabel0:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 0)
  br label %ifLabel2
  ; ELSE branch
  ifLabel1:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 1)
  br label %ifLabel2
  ifLabel2:
  ; store assigned value
  store i32 1, i32* %xPtr
  ; load value from variable
  %temp2 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp3 = icmp ne i32 %temp2, 0
  br i1 %temp3, label %ifLabel3, label %ifLabel4
  ; THEN branch
  ifLabel3:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 0)
  br label %ifLabel5
  ; ELSE branch
  ifLabel4:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 1)
  br label %ifLabel5
  ifLabel5:
  ; store assigned value
  store i32 0, i32* %xPtr
  ; load value from variable
  %temp4 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp5 = icmp ne i32 %temp4, 0
  br i1 %temp5, label %ifLabel6, label %ifLabel7
  ; THEN branch
  ifLabel6:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 2)
  br label %ifLabel8
  ; ELSE branch
  ifLabel7:
  br label %ifLabel8
  ifLabel8:
  ; store assigned value
  store i32 1, i32* %xPtr
  ; load value from variable
  %temp6 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp7 = icmp ne i32 %temp6, 0
  br i1 %temp7, label %ifLabel9, label %ifLabel10
  ; THEN branch
  ifLabel9:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 3)
  br label %ifLabel11
  ; ELSE branch
  ifLabel10:
  br label %ifLabel11
  ifLabel11:
  ; load value from variable
  %temp8 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp9 = icmp ne i32 %temp8, 0
  br i1 %temp9, label %ifLabel12, label %ifLabel13
  ; THEN branch
  ifLabel12:
  ; load value from variable
  %temp10 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp11 = icmp ne i32 %temp10, 0
  br i1 %temp11, label %ifLabel15, label %ifLabel16
  ; THEN branch
  ifLabel15:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 4)
  br label %ifLabel17
  ; ELSE branch
  ifLabel16:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 5)
  br label %ifLabel17
  ifLabel17:
  br label %ifLabel14
  ; ELSE branch
  ifLabel13:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 6)
  br label %ifLabel14
  ifLabel14:
  ; load value from variable
  %temp12 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp13 = icmp ne i32 %temp12, 0
  br i1 %temp13, label %ifLabel18, label %ifLabel19
  ; THEN branch
  ifLabel18:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 7)
  br label %ifLabel20
  ; ELSE branch
  ifLabel19:
  ; load value from variable
  %temp14 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp15 = icmp ne i32 %temp14, 0
  br i1 %temp15, label %ifLabel21, label %ifLabel22
  ; THEN branch
  ifLabel21:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 8)
  br label %ifLabel23
  ; ELSE branch
  ifLabel22:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 9)
  br label %ifLabel23
  ifLabel23:
  br label %ifLabel20
  ifLabel20:
  ; store assigned value
  store i32 0, i32* %xPtr
  ; load value from variable
  %temp16 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp17 = icmp ne i32 %temp16, 0
  br i1 %temp17, label %ifLabel24, label %ifLabel25
  ; THEN branch
  ifLabel24:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 10)
  br label %ifLabel26
  ; ELSE branch
  ifLabel25:
  ; load value from variable
  %temp18 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp19 = icmp ne i32 %temp18, 0
  br i1 %temp19, label %ifLabel27, label %ifLabel28
  ; THEN branch
  ifLabel27:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 11)
  br label %ifLabel29
  ; ELSE branch
  ifLabel28:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 12)
  br label %ifLabel29
  ifLabel29:
  br label %ifLabel26
  ifLabel26:
  ; load value from variable
  %temp20 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp21 = icmp ne i32 %temp20, 0
  br i1 %temp21, label %ifLabel30, label %ifLabel31
  ; THEN branch
  ifLabel30:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 13)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 14)
  br label %ifLabel32
  ; ELSE branch
  ifLabel31:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 15)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 16)
  br label %ifLabel32
  ifLabel32:
  ; store assigned value
  store i32 1, i32* %xPtr
  ; load value from variable
  %temp22 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp23 = icmp ne i32 %temp22, 0
  br i1 %temp23, label %ifLabel33, label %ifLabel34
  ; THEN branch
  ifLabel33:
  br label %ifLabel35
  ; ELSE branch
  ifLabel34:
  br label %ifLabel35
  ifLabel35:
  ; load value from variable
  %temp24 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp25 = icmp ne i32 %temp24, 0
  br i1 %temp25, label %ifLabel36, label %ifLabel37
  ; THEN branch
  ifLabel36:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 17)
  br label %ifLabel38
  ; ELSE branch
  ifLabel37:
  br label %ifLabel38
  ifLabel38:
  ret void
}
