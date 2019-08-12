; source file: constructorCall.t
; T version: 1.0
; compiled: Mon Aug 12 15:44:53 EDT 2019

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
@B$VMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @A$VMT to i8*)]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$A =  type { i8* }
%class$B =  type { i8* }
%class$Object =  type { i8* }

; constructor declaration for class B
define void @B$constructor$( i8* %context ) {
  ; copy constructor params
  %temp0 = bitcast i8* %context to %class$B* 
  %temp1 = load %class$B, %class$B* %temp0
  %this = alloca %class$B
  store %class$B %temp1, %class$B* %this
  ; rest of constructor body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 17)
  %temp2 = load %class$B, %class$B* %this
  store %class$B %temp2, %class$B* %temp0
  ret void
}

; constructor declaration for class A
define void @A$constructor$( i8* %context ) {
  ; copy constructor params
  %temp3 = bitcast i8* %context to %class$A* 
  %temp4 = load %class$A, %class$A* %temp3
  %this = alloca %class$A
  store %class$A %temp4, %class$A* %this
  ; rest of constructor body
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 7)
  %temp5 = load %class$A, %class$A* %this
  store %class$A %temp5, %class$A* %temp3
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
  %temp7 = getelementptr %class$A*, %class$A** null, i32 1
  %temp8 = ptrtoint %class$A** %temp7 to i64
  %temp9 = call i8* @t_rt_alloc(i64 %temp8, i32 7)
  %temp10 = bitcast i8* %temp9 to [1 x i8*]**
  store [1 x i8*]* @A$VMT, [1 x i8*]** %temp10
  %temp6 = bitcast i8* %temp9 to %class$A*
  call void @A$constructor$(i8* %temp9)
  ; store assigned value
  store %class$A* %temp6, %class$A** %a
  ; declaration statement
  %b = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %b
  ; class instance creation
  %temp12 = getelementptr %class$B*, %class$B** null, i32 1
  %temp13 = ptrtoint %class$B** %temp12 to i64
  %temp14 = call i8* @t_rt_alloc(i64 %temp13, i32 9)
  %temp15 = bitcast i8* %temp14 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp15
  %temp11 = bitcast i8* %temp14 to %class$B*
  call void @B$constructor$(i8* %temp14)
  ; store assigned value
  store %class$B* %temp11, %class$B** %b
  ret void
}
