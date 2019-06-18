; source file: simpleIf.t
; T version: 1.0
; compiled: Sat Jun 01 20:09:40 EDT 2019

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
  ; store assigned value
  store i32 1, i32* %xPtr
  ; load value from variable
  %temp0 = load i32, i32* %xPtr
  ; check condition for IF statement
  %temp1 = icmp ne i32 %temp0, 0
  br i1 %temp1, label %ifLabel0, label %ifLabel1
  ; THEN branch
  ifLabel0:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 13)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 14)
  br label %ifLabel2
  ; ELSE branch
  ifLabel1:
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 15)
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 16)
  br label %ifLabel2
  ifLabel2:
  ret void
}
