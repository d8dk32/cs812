; source file: fieldAccess4.t
; T version: 1.0
; compiled: Fri Jul 12 23:15:05 EDT 2019

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
@B$VMT = global [1 x i8*] [i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]
@Object$VMT = global [1 x i8*] [i8* null]

;array type
%array$int = type { i8*, i8*, i32, i32, [0 x i32] }
%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }
; class object types
%class$A =  type { i8*, i32, i32 }
%class$B =  type { i8*, %class$A*, i32 }
%class$Object =  type { i8* }

; main block
define void @main() {
  call void @t_rt_alloc_init()
  ; declaration statement
  %b = alloca %class$B*
  ; store empty value on declaration
  store %class$B* null, %class$B** %b
  ; class instance creation
  %temp1 = getelementptr %class$B*, %class$B** null, i32 3
  %temp2 = ptrtoint %class$B** %temp1 to i64
  %temp3 = call i8* @t_rt_alloc(i64 %temp2, i32 5)
  %temp4 = bitcast i8* %temp3 to [1 x i8*]**
  store [1 x i8*]* @B$VMT, [1 x i8*]** %temp4
  %temp0 = bitcast i8* %temp3 to %class$B*
  ; store assigned value
  store %class$B* %temp0, %class$B** %b
  ; class instance creation
  %temp6 = getelementptr %class$A*, %class$A** null, i32 3
  %temp7 = ptrtoint %class$A** %temp6 to i64
  %temp8 = call i8* @t_rt_alloc(i64 %temp7, i32 6)
  %temp9 = bitcast i8* %temp8 to [1 x i8*]**
  store [1 x i8*]* @A$VMT, [1 x i8*]** %temp9
  %temp5 = bitcast i8* %temp8 to %class$A*
  ; load value from variable
  %temp10 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp11 = icmp eq %class$B* %temp10, null
  br i1 %temp11, label %label0, label %label1
  label0:
  call void @t_rt_print_null_ref_error(i32 6)
  br label %label1
  label1:
  %temp13 = getelementptr %class$B, %class$B* %temp10, i32 0, i32 1
  ; store assigned value
  store %class$A* %temp5, %class$A** %temp13
  ; load value from variable
  %temp15 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp16 = icmp eq %class$B* %temp15, null
  br i1 %temp16, label %label2, label %label3
  label2:
  call void @t_rt_print_null_ref_error(i32 7)
  br label %label3
  label3:
  %temp18 = getelementptr %class$B, %class$B* %temp15, i32 0, i32 2
  ; store assigned value
  store i32 9, i32* %temp18
  ; load value from variable
  %temp20 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp21 = icmp eq %class$B* %temp20, null
  br i1 %temp21, label %label4, label %label5
  label4:
  call void @t_rt_print_null_ref_error(i32 8)
  br label %label5
  label5:
  %temp23 = getelementptr %class$B, %class$B* %temp20, i32 0, i32 1
  %temp25 = load %class$A*, %class$A** %temp23
  ; field access
  ; Null reference check
  %temp26 = icmp eq %class$A* %temp25, null
  br i1 %temp26, label %label6, label %label7
  label6:
  call void @t_rt_print_null_ref_error(i32 8)
  br label %label7
  label7:
  %temp28 = getelementptr %class$A, %class$A* %temp25, i32 0, i32 1
  ; store assigned value
  store i32 22, i32* %temp28
  ; load value from variable
  %temp30 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp31 = icmp eq %class$B* %temp30, null
  br i1 %temp31, label %label8, label %label9
  label8:
  call void @t_rt_print_null_ref_error(i32 9)
  br label %label9
  label9:
  %temp33 = getelementptr %class$B, %class$B* %temp30, i32 0, i32 1
  %temp35 = load %class$A*, %class$A** %temp33
  ; field access
  ; Null reference check
  %temp36 = icmp eq %class$A* %temp35, null
  br i1 %temp36, label %label10, label %label11
  label10:
  call void @t_rt_print_null_ref_error(i32 9)
  br label %label11
  label11:
  %temp38 = getelementptr %class$A, %class$A* %temp35, i32 0, i32 2
  ; store assigned value
  store i32 11, i32* %temp38
  ; load value from variable
  %temp40 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp41 = icmp eq %class$B* %temp40, null
  br i1 %temp41, label %label12, label %label13
  label12:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label13
  label13:
  %temp43 = getelementptr %class$B, %class$B* %temp40, i32 0, i32 1
  %temp45 = load %class$A*, %class$A** %temp43
  ; field access
  ; Null reference check
  %temp46 = icmp eq %class$A* %temp45, null
  br i1 %temp46, label %label14, label %label15
  label14:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label15
  label15:
  %temp48 = getelementptr %class$A, %class$A* %temp45, i32 0, i32 1
  %temp50 = load i32, i32* %temp48
  ; load value from variable
  %temp51 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp52 = icmp eq %class$B* %temp51, null
  br i1 %temp52, label %label16, label %label17
  label16:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label17
  label17:
  %temp54 = getelementptr %class$B, %class$B* %temp51, i32 0, i32 1
  %temp56 = load %class$A*, %class$A** %temp54
  ; field access
  ; Null reference check
  %temp57 = icmp eq %class$A* %temp56, null
  br i1 %temp57, label %label18, label %label19
  label18:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label19
  label19:
  %temp59 = getelementptr %class$A, %class$A* %temp56, i32 0, i32 2
  %temp61 = load i32, i32* %temp59
  ; add
  %temp62 = add i32 %temp50, %temp61
  ; load value from variable
  %temp63 = load %class$B*, %class$B** %b
  ; field access
  ; Null reference check
  %temp64 = icmp eq %class$B* %temp63, null
  br i1 %temp64, label %label20, label %label21
  label20:
  call void @t_rt_print_null_ref_error(i32 10)
  br label %label21
  label21:
  %temp66 = getelementptr %class$B, %class$B* %temp63, i32 0, i32 2
  %temp68 = load i32, i32* %temp66
  ; add
  %temp69 = add i32 %temp62, %temp68
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp69)
  ret void
}
