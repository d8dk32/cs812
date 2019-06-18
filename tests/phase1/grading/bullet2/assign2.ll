; source file: assign2.t
; T version: 1.0
; compiled: Fri May 31 15:44:38 EDT 2019

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
  %iPtr = alloca i32
  ; store result
  store i32 0, i32* %iPtr
  ; declaration statement
  %jPtr = alloca i32
  ; store result
  store i32 0, i32* %jPtr
  ; store result in variable
  store i32 21, i32* %jPtr
  ; store result in variable
  store i32 21, i32* %iPtr
  ; load value from variable
  %temp0 = load i32, i32* %iPtr
  ; load value from variable
  %temp1 = load i32, i32* %jPtr
  ; add
  %temp2 = add i32 %temp0, %temp1
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp2)
  ret void
}
