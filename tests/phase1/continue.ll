; source file: continue.t
; T version: 1.0
; compiled: Sat Jun 01 23:50:43 EDT 2019

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
  ; declaration statement
  %yPtr = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %yPtr
  ; store assigned value
  store i32 5, i32* %xPtr
  ; store assigned value
  store i32 0, i32* %yPtr
  ; start while loop
  br label %label0
  label0:
  ; load value from variable
  %temp0 = load i32, i32* %xPtr
  ; evaluate while condition
  %temp1 = icmp ne i32 %temp0, 0
  br i1 %temp1, label %label2, label %label1
  ; while statement body
  label2:
  ; load value from variable
  %temp2 = load i32, i32* %xPtr
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp2)
  ; load value from variable
  %temp3 = load i32, i32* %yPtr
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp3)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 1999)
  ; load value from variable
  %temp4 = load i32, i32* %xPtr
  ; negate
  %temp5 = sub i32 0, 1
  ; add
  %temp6 = add i32 %temp4, %temp5
  ; store assigned value
  store i32 %temp6, i32* %xPtr
  ; load value from variable
  %temp7 = load i32, i32* %yPtr
  ; check condition for IF statement
  %temp8 = icmp ne i32 %temp7, 0
  br i1 %temp8, label %label3, label %label4
  ; THEN branch
  label3:
  ;continue
  br label %label0
  br label %label5
  ; ELSE branch
  label4:
  br label %label5
  label5:
  ; load value from variable
  %temp9 = load i32, i32* %yPtr
  ; add
  %temp10 = add i32 %temp9, 1
  ; store assigned value
  store i32 %temp10, i32* %yPtr
  br label %label0
  ; end while statement body
  label1:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 2001)
  ret void
}
