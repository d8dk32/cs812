; source file: fieldAccess.t
; T version: 1.0
; compiled: Fri Jul 12 13:55:51 EDT 2019

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
@A$VMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$A =  type { i8*, i32 }
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %a = alloca %class$A*
  ; store empty value on declaration
  store %class$A* null, %class$A** %a
  ; class instance creation
  %temp1 = getelementptr %class$A*, %class$A** null, i32 2
  %temp2 = ptrtoint %class$A** %temp1 to i64
  %temp3 = call i8* @t_rt_alloc(i64 %temp2, i32 7)
  %temp4 = bitcast i8* %temp3 to [1 x i8*]**
  store [1 x i8*]* @A$VMT, [1 x i8*]** %temp4
  %temp0 = bitcast i8* %temp3 to %class$A*
  ; store assigned value
  store %class$A* %temp0, %class$A** %a
  ; load value from variable
  %temp5 = load %class$A*, %class$A** %a
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp8)
  ; load value from variable
  %temp10 = load %class$A*, %class$A** %a
  ; store assigned value
  store i32 42, i32* %temp13
  ; load value from variable
  %temp15 = load %class$A*, %class$A** %a
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp18)
  ret void
}
