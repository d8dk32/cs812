; source file: createClassInstance.t
; T version: 1.0
; compiled: Fri Jul 12 21:54:00 EDT 2019

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
@B$VMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @A$VMT to i8*)]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$A =  type { i8*, %class$A*, %class$A*, %class$B*, %class$B* }
%class$B =  type { i8*, i32, i32, %class$A*, %class$A*, %class$B*, %class$B* }
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %a = alloca %class$A*
  ; store empty value on declaration
  store %class$A* null, %class$A** %a
  ; declaration statement
  %b = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %b
  ; class instance creation
  %temp1 = getelementptr %class$A*, %class$A** null, i32 5
  %temp2 = ptrtoint %class$A** %temp1 to i64
  %temp3 = call i8* @t_rt_alloc(i64 %temp2, i32 16)
  %temp4 = bitcast i8* %temp3 to [1 x i8*]**
  store [1 x i8*]* @A$VMT, [1 x i8*]** %temp4
  %temp0 = bitcast i8* %temp3 to %class$A*
  ; store assigned value
  store %class$A* %temp0, %class$A** %a
  ; class instance creation
  %temp6 = getelementptr %class$B*, %class$B** null, i32 7
  %temp7 = ptrtoint %class$B** %temp6 to i64
  %temp8 = call i8* @t_rt_alloc(i64 %temp7, i32 17)
  %temp9 = bitcast i8* %temp8 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp9
  %temp5 = bitcast i8* %temp8 to %class$B*
  %temp10 = bitcast %class$B* %temp5 to %class$A*
  ; store assigned value
  store %class$A* %temp10, %class$A** %a
  ; class instance creation
  %temp12 = getelementptr %class$B*, %class$B** null, i32 7
  %temp13 = ptrtoint %class$B** %temp12 to i64
  %temp14 = call i8* @t_rt_alloc(i64 %temp13, i32 18)
  %temp15 = bitcast i8* %temp14 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp15
  %temp11 = bitcast i8* %temp14 to %class$B*
  ; store assigned value
  store %class$B* %temp11, %class$B** %b
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 42)
  ret void
}
