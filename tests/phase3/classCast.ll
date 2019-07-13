; source file: classCast.t
; T version: 1.0
; compiled: Fri Jul 12 23:18:42 EDT 2019

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
@C$VMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @B$VMT to i8*)]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$A =  type { i8* }
%class$B =  type { i8* }
%class$C =  type { i8* }
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
  ; declaration statement
  %c = alloca %class$C*
  ; store empty value on declaration
  store %class$C* null, %class$C** %c
  ; declaration statement
  %d = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %d
  ; declaration statement
  %e = alloca %class$C*
  ; store empty value on declaration
  store %class$C* null, %class$C** %e
  ; load value from variable
  %temp0 = load %class$A*, %class$A** %a
  %temp1 = bitcast %class$A* %temp0 to %class$B*
  ; store assigned value
  store %class$B* %temp1, %class$B** %b
  ; load value from variable
  %temp2 = load %class$A*, %class$A** %a
  %temp3 = bitcast %class$A* %temp2 to %class$C*
  ; store assigned value
  store %class$C* %temp3, %class$C** %c
  ; class instance creation
  %temp5 = getelementptr %class$B*, %class$B** null, i32 1
  %temp6 = ptrtoint %class$B** %temp5 to i64
  %temp7 = call i8* @t_rt_alloc(i64 %temp6, i32 19)
  %temp8 = bitcast i8* %temp7 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp8
  %temp4 = bitcast i8* %temp7 to %class$B*
  ; store assigned value
  store %class$B* %temp4, %class$B** %b
  ; class instance creation
  %temp10 = getelementptr %class$C*, %class$C** null, i32 1
  %temp11 = ptrtoint %class$C** %temp10 to i64
  %temp12 = call i8* @t_rt_alloc(i64 %temp11, i32 20)
  %temp13 = bitcast i8* %temp12 to [1 x i8*]**
  store [1 x i8*]* @C$VMT, [1 x i8*]** %temp13
  %temp9 = bitcast i8* %temp12 to %class$C*
  ; store assigned value
  store %class$C* %temp9, %class$C** %c
  ; class instance creation
  %temp15 = getelementptr %class$C*, %class$C** null, i32 1
  %temp16 = ptrtoint %class$C** %temp15 to i64
  %temp17 = call i8* @t_rt_alloc(i64 %temp16, i32 21)
  %temp18 = bitcast i8* %temp17 to [1 x i8*]**
  store [1 x i8*]* @C$VMT, [1 x i8*]** %temp18
  %temp14 = bitcast i8* %temp17 to %class$C*
  ; store assigned value
  store %class$C* %temp14, %class$C** %e
  ; load value from variable
  %temp19 = load %class$C*, %class$C** %e
  %temp20 = bitcast %class$C* %temp19 to %class$B*
  ; store assigned value
  store %class$B* %temp20, %class$B** %d
  ; load value from variable
  %temp21 = load %class$B*, %class$B** %b
  %temp22 = bitcast %class$B* %temp21 to %class$A*
  ; store assigned value
  store %class$A* %temp22, %class$A** %a
  ; load value from variable
  %temp23 = load %class$A*, %class$A** %a
  %temp24 = bitcast %class$A* %temp23 to %class$B*
  ; store assigned value
  store %class$B* %temp24, %class$B** %b
  ; load value from variable
  %temp25 = load %class$C*, %class$C** %c
  %temp26 = bitcast %class$C* %temp25 to %class$A*
  ; store assigned value
  store %class$A* %temp26, %class$A** %a
  ; load value from variable
  %temp27 = load %class$A*, %class$A** %a
  %temp28 = bitcast %class$A* %temp27 to %class$C*
  ; store assigned value
  store %class$C* %temp28, %class$C** %c
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 1066)
  ret void
}
