; source file: precedence.t
; T version: 1.0
; compiled: Sun Jun 02 15:02:27 EDT 2019

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)
declare void @t_rt_print_divide_by_zero_error(i32)

; VMTs
@arrayVMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@intVMT = global [1 x i8*] [i8* null]
@Object$VMT = global [1 x i8*] [i8* null]

; class object types
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; multiply
  %temp0 = mul i32 2, 20
  ; add
  %temp1 = add i32 2, %temp0
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp1)
  ; multiply
  %temp2 = mul i32 2, 10
  ; subtract
  %temp3 = sub i32 62, %temp2
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp3)
  ; divide
  ; check for divide by 0
  %temp5 = icmp eq i32 2, 0
  br i1 %temp5, label %label0, label %label1
  label0:
  call void @t_rt_print_divide_by_zero_error(i32 5)
  ret void
  label1:
  %temp4 = sdiv i32 80, 2
  ; add
  %temp6 = add i32 2, %temp4
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp6)
  ; divide
  ; check for divide by 0
  %temp8 = icmp eq i32 2, 0
  br i1 %temp8, label %label2, label %label3
  label2:
  call void @t_rt_print_divide_by_zero_error(i32 6)
  ret void
  label3:
  %temp7 = sdiv i32 20, 2
  ; subtract
  %temp9 = sub i32 52, %temp7
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp9)
  ; negate
  %temp10 = sub i32 0, 2
  ; add
  %temp11 = add i32 %temp10, 44
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp11)
  ; negate
  %temp12 = sub i32 0, 20
  ; subtract
  %temp13 = sub i32 %temp12, 22
  ; negate
  %temp14 = sub i32 0, %temp13
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp14)
  ; not
  %temp16 = icmp eq i32 0, 0
  %temp15 = zext i1 %temp16 to i32
  ; add
  %temp17 = add i32 %temp15, 41
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp17)
  ; add
  %temp18 = add i32 5, 6
  ; greater than
  %temp20 = icmp sgt i32 6, %temp18
  %temp19 = zext i1 %temp20 to i32
  ; greater than
  %temp22 = icmp sgt i32 %temp19, 1
  %temp21 = zext i1 %temp22 to i32
  ; add
  %temp23 = add i32 %temp21, 42
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp23)
  ; subtract
  %temp24 = sub i32 5, 2
  ; greater than
  %temp26 = icmp sgt i32 6, %temp24
  %temp25 = zext i1 %temp26 to i32
  ; greater than
  %temp28 = icmp sgt i32 %temp25, 0
  %temp27 = zext i1 %temp28 to i32
  ; add
  %temp29 = add i32 %temp27, 41
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp29)
  ; multiply
  %temp30 = mul i32 5, 6
  ; greater than
  %temp32 = icmp sgt i32 6, %temp30
  %temp31 = zext i1 %temp32 to i32
  ; greater than
  %temp34 = icmp sgt i32 %temp31, 1
  %temp33 = zext i1 %temp34 to i32
  ; add
  %temp35 = add i32 %temp33, 42
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp35)
  ; divide
  ; check for divide by 0
  %temp37 = icmp eq i32 2, 0
  br i1 %temp37, label %label4, label %label5
  label4:
  call void @t_rt_print_divide_by_zero_error(i32 13)
  ret void
  label5:
  %temp36 = sdiv i32 5, 2
  ; greater than
  %temp39 = icmp sgt i32 6, %temp36
  %temp38 = zext i1 %temp39 to i32
  ; greater than
  %temp41 = icmp sgt i32 %temp38, 0
  %temp40 = zext i1 %temp41 to i32
  ; add
  %temp42 = add i32 %temp40, 41
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp42)
  ; greater than
  %temp44 = icmp sgt i32 6, 3
  %temp43 = zext i1 %temp44 to i32
  ; equals
  %temp46 = icmp eq i32 1, %temp43
  %temp45 = zext i1 %temp46 to i32
  ; add
  %temp47 = add i32 %temp45, 41
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp47)
  ; less than
  %temp49 = icmp slt i32 3, 0
  %temp48 = zext i1 %temp49 to i32
  ; equals
  %temp51 = icmp eq i32 0, %temp48
  %temp50 = zext i1 %temp51 to i32
  ; add
  %temp52 = add i32 %temp50, 41
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp52)
  ret void
}
