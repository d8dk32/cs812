; source file: constructorCall2.t
; T version: 1.0
; compiled: Mon Aug 12 15:45:25 EDT 2019

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
declare void @t_rt_runtime_cast_check(i32, i8*, i8*)

; VMTs
@arrayVMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@intVMT = global [1 x i8*] [i8* null]
@A$VMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$A =  type { i8* }
%class$Object =  type { i8* }

; constructor declaration for class A
define void @A$constructor$int$( i8* %context, i32 %param0 ) {
  ; copy constructor params
  %i = alloca i32
  store i32 %param0, i32* %i
  %temp0 = bitcast i8* %context to %class$A* 
  %temp1 = load %class$A, %class$A* %temp0
  %this = alloca %class$A
  store %class$A %temp1, %class$A* %this
  ; rest of constructor body
  ; load value from variable
  %temp2 = load i32, i32* %i
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp2)
  %temp3 = load %class$A, %class$A* %this
  store %class$A %temp3, %class$A* %temp0
  ret void
}

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %a = alloca %class$A*
  ; store empty value on declaration
  store %class$A* null, %class$A** %a
  ; class instance creation
  %temp5 = getelementptr %class$A*, %class$A** null, i32 1
  %temp6 = ptrtoint %class$A** %temp5 to i64
  %temp7 = call i8* @t_rt_alloc(i64 %temp6, i32 7)
  %temp8 = bitcast i8* %temp7 to [1 x i8*]**
  store [1 x i8*]* @A$VMT, [1 x i8*]** %temp8
  %temp4 = bitcast i8* %temp7 to %class$A*
  call void @A$constructor$int$(i8* %temp7, i32 42)
  ; store assigned value
  store %class$A* %temp4, %class$A** %a
  ret void
}
