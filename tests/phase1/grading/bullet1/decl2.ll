; source file: decl2.t
; T version: 1.0
; compiled: Thu May 30 14:42:04 EDT 2019

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
  ret void
}
