; source file: fieldAccess5.t
; T version: 1.0
; compiled: Fri Jul 12 23:18:11 EDT 2019

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
%class$A =  type { i8*, i32, i32 }
%class$B =  type { i8*, i32, i32, i32, i32 }
%class$C =  type { i8*, i32, i32, i32, i32, i32 }
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %b = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %b
  ; class instance creation
  %temp1 = getelementptr %class$B*, %class$B** null, i32 5
  %temp2 = ptrtoint %class$B** %temp1 to i64
  %temp3 = call i8* @t_rt_alloc(i64 %temp2, i32 4)
  %temp4 = bitcast i8* %temp3 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp4
  %temp0 = bitcast i8* %temp3 to %class$B*
  ; store assigned value
  store %class$B* %temp0, %class$B** %b
  ; load value from variable
  %temp5 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp6 = icmp eq %class$B* %temp5, null
  br i1 %temp6, label %label0, label %label1
  label0:
  call void @t_rt_print_null_ref_error(i32 5)
  br label %label1
  label1:
  %temp8 = getelementptr %class$B, %class$B* %temp5, i32 0, i32 4
  ; store assigned value
  store i32 20, i32* %temp8
  ; load value from variable
  %temp10 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp11 = icmp eq %class$B* %temp10, null
  br i1 %temp11, label %label2, label %label3
  label2:
  call void @t_rt_print_null_ref_error(i32 6)
  br label %label3
  label3:
  %temp13 = getelementptr %class$B, %class$B* %temp10, i32 0, i32 3
  ; store assigned value
  store i32 22, i32* %temp13
  ; declaration statement
  %c = alloca %class$C*
  ; store empty value on declaration
  store %class$C* null, %class$C** %c
  ; class instance creation
  %temp16 = getelementptr %class$C*, %class$C** null, i32 6
  %temp17 = ptrtoint %class$C** %temp16 to i64
  %temp18 = call i8* @t_rt_alloc(i64 %temp17, i32 8)
  %temp19 = bitcast i8* %temp18 to [1 x i8*]**
  store [1 x i8*]* @C$VMT, [1 x i8*]** %temp19
  %temp15 = bitcast i8* %temp18 to %class$C*
  ; store assigned value
  store %class$C* %temp15, %class$C** %c
  ; load value from variable
  %temp20 = load %class$C*, %class$C** %c
  ; field access
  ; Null reference check
  %temp21 = icmp eq %class$C* %temp20, null
  br i1 %temp21, label %label4, label %label5
  label4:
  call void @t_rt_print_null_ref_error(i32 9)
  br label %label5
  label5:
  %temp23 = getelementptr %class$C, %class$C* %temp20, i32 0, i32 5
  ; store assigned value
  store i32 1, i32* %temp23
  ; load value from variable
  %temp25 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp26 = icmp eq %class$B* %temp25, null
  br i1 %temp26, label %label6, label %label7
  label6:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label7
  label7:
  %temp28 = getelementptr %class$B, %class$B* %temp25, i32 0, i32 3
  %temp30 = load i32, i32* %temp28
  ; load value from variable
  %temp31 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp32 = icmp eq %class$B* %temp31, null
  br i1 %temp32, label %label8, label %label9
  label8:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label9
  label9:
  %temp34 = getelementptr %class$B, %class$B* %temp31, i32 0, i32 4
  %temp36 = load i32, i32* %temp34
  ; add
  %temp37 = add i32 %temp30, %temp36
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp37)
  ; declaration statement
  %a = alloca %class$A*
  ; store empty value on declaration
  store %class$A* null, %class$A** %a
  ; load value from variable
  %temp38 = load %class$C*, %class$C** %c
  %temp39 = bitcast %class$C* %temp38 to %class$A*
  ; store assigned value
  store %class$A* %temp39, %class$A** %a
  ; load value from variable
  %temp40 = load %class$A*, %class$A** %a
  ; field access
  ; Null reference check
  %temp41 = icmp eq %class$A* %temp40, null
  br i1 %temp41, label %label10, label %label11
  label10:
  call void @t_rt_print_null_ref_error(i32 13)
  br label %label11
  label11:
  %temp43 = getelementptr %class$A, %class$A* %temp40, i32 0, i32 1
  %temp45 = load i32, i32* %temp43
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp45)
  ret void
}
