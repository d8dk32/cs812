; source file: while.t
; T version: 1.0
; compiled: Mon Jun 24 01:55:41 EDT 2019

; declarations for the runtime support functions
declare void @t_rt_alloc_init()
declare i8* @t_rt_alloc(i64, i32)
declare void @t_rt_dealloc(i8*)
declare void @t_rt_print_int(i32)
declare void @t_rt_print_divide_by_zero_error(i32)
declare void @t_rt_print_array_index_out_of_bounds_error(i32)
declare void @t_rt_print_null_ref_error(i32)
declare void @t_rt_print_logging(i32)
declare i8* @t_rt_new_intarray(i32, i8*, i32, i32)
declare i8* @t_rt_new_refarray(i32, i8*, i32, i32)

; VMTs
@arrayVMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@intVMT = global [1 x i8*] [i8* null]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %x = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %x
  ; store assigned value
  store i32 10, i32* %x
  ; start while loop
  br label %label0
  label0:
  ; load value from variable
  %temp0 = load i32, i32* %x
  ; evaluate while condition
  %temp1 = icmp ne i32 %temp0, 0
  br i1 %temp1, label %label2, label %label1
  ; while statement body
  label2:
  ; load value from variable
  %temp2 = load i32, i32* %x
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp2)
  ; load value from variable
  %temp3 = load i32, i32* %x
  ; negate
  %temp4 = sub i32 0, 1
  ; add
  %temp5 = add i32 %temp3, %temp4
  ; store assigned value
  store i32 %temp5, i32* %x
  br label %label0
  ; end while statement body
  label1:
  ; start while loop
  br label %label3
  label3:
  ; load value from variable
  %temp6 = load i32, i32* %x
  ; evaluate while condition
  %temp7 = icmp ne i32 %temp6, 0
  br i1 %temp7, label %label5, label %label4
  ; while statement body
  label5:
  ; load value from variable
  %temp8 = load i32, i32* %x
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp8)
  br label %label3
  ; end while statement body
  label4:
  ; start while loop
  br label %label6
  label6:
  ; load value from variable
  %temp9 = load i32, i32* %x
  ; evaluate while condition
  %temp10 = icmp ne i32 %temp9, 0
  br i1 %temp10, label %label8, label %label7
  ; while statement body
  label8:
  br label %label6
  ; end while statement body
  label7:
  ; start while loop
  br label %label9
  label9:
  ; load value from variable
  %temp11 = load i32, i32* %x
  ; evaluate while condition
  %temp12 = icmp ne i32 %temp11, 0
  br i1 %temp12, label %label11, label %label10
  ; while statement body
  label11:
  br label %label9
  ; end while statement body
  label10:
  ; store assigned value
  store i32 5, i32* %x
  ; declaration statement
  %y = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %y
  ; start while loop
  br label %label12
  label12:
  ; load value from variable
  %temp13 = load i32, i32* %x
  ; evaluate while condition
  %temp14 = icmp ne i32 %temp13, 0
  br i1 %temp14, label %label14, label %label13
  ; while statement body
  label14:
  ; load value from variable
  %temp15 = load i32, i32* %x
  ; store assigned value
  store i32 %temp15, i32* %y
  ; start while loop
  br label %label15
  label15:
  ; load value from variable
  %temp16 = load i32, i32* %y
  ; evaluate while condition
  %temp17 = icmp ne i32 %temp16, 0
  br i1 %temp17, label %label17, label %label16
  ; while statement body
  label17:
  ; load value from variable
  %temp18 = load i32, i32* %x
  ; load value from variable
  %temp19 = load i32, i32* %y
  ; add
  %temp20 = add i32 %temp18, %temp19
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp20)
  ; load value from variable
  %temp21 = load i32, i32* %y
  ; negate
  %temp22 = sub i32 0, 1
  ; add
  %temp23 = add i32 %temp21, %temp22
  ; store assigned value
  store i32 %temp23, i32* %y
  br label %label15
  ; end while statement body
  label16:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 1999)
  ; load value from variable
  %temp24 = load i32, i32* %x
  ; negate
  %temp25 = sub i32 0, 1
  ; add
  %temp26 = add i32 %temp24, %temp25
  ; store assigned value
  store i32 %temp26, i32* %x
  br label %label12
  ; end while statement body
  label13:
  ret void
}
