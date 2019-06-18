; source file: great.t
; T version: 1.0
; compiled: Sun Jun 02 11:46:55 EDT 2019

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
  ; greater than
  %temp1 = icmp sgt i32 4, 24
  %temp0 = zext i1 %temp1 to i32
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp0)
  ; greater than
  %temp3 = icmp sgt i32 44, 2
  %temp2 = zext i1 %temp3 to i32
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp2)
  ret void
}
