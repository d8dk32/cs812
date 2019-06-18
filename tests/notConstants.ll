; source file: notConstants.t
; T version: 1.0
; compiled: Sun Jun 02 10:44:38 EDT 2019

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
  ; not
  %temp1 = icmp eq i32 42, 0
  %temp0 = zext i1 %temp1 to i32
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp0)
  ; not
  %temp3 = icmp eq i32 42, 0
  %temp2 = zext i1 %temp3 to i32
  ; not
  %temp5 = icmp eq i32 %temp2, 0
  %temp4 = zext i1 %temp5 to i32
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp4)
  ; declaration statement
  %xPtr = alloca i32
  ; store 0 on declaration
  store i32 0, i32* %xPtr
  ; store assigned value
  store i32 1, i32* %xPtr
  ; load value from variable
  %temp6 = load i32, i32* %xPtr
  ; not
  %temp8 = icmp eq i32 %temp6, 0
  %temp7 = zext i1 %temp8 to i32
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp7)
  ; load value from variable
  %temp9 = load i32, i32* %xPtr
  ; not
  %temp11 = icmp eq i32 %temp9, 0
  %temp10 = zext i1 %temp11 to i32
  ; not
  %temp13 = icmp eq i32 %temp10, 0
  %temp12 = zext i1 %temp13 to i32
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp12)
  ret void
}
