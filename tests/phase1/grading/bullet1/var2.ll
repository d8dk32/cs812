; source file: var2.t
; T version: 1.0
; compiled: Fri May 31 07:46:31 EDT 2019

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
  ; store result
  store i32 0, i32* %xPtr
  ; load value from variable
  %temp0 = load i32, i32* %xPtr
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp0)
  ; declaration statement
  %aPtr = alloca i32
  ; store result
  store i32 0, i32* %aPtr
  ; declaration statement
  %bPtr = alloca i32
  ; store result
  store i32 0, i32* %bPtr
  ; declaration statement
  %cPtr = alloca i32
  ; store result
  store i32 0, i32* %cPtr
  ; load value from variable
  %temp1 = load i32, i32* %aPtr
  ; load value from variable
  %temp2 = load i32, i32* %bPtr
  ; add
  %temp3 = add i32 %temp1, %temp2
  ; load value from variable
  %temp4 = load i32, i32* %cPtr
  ; add
  %temp5 = add i32 %temp3, %temp4
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp5)
  ret void
}
