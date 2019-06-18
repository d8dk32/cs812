; source file: simpleWhile.t
; T version: 1.0
; compiled: Sat Jun 01 22:19:09 EDT 2019

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
  ; store 0 on declaration
  store i32 0, i32* %xPtr
  ; store assigned value
  store i32 10, i32* %xPtr
  ; start while loop
  br label %label0
  label0:
  ; load value from variable
  %temp0 = load i32, i32* %xPtr
  ; evaluate while condition
  %temp1 = icmp ne i32 %temp0, 0
  br i1 %temp1, label %label2, label %label1
  ; while statement body
  label2:
  ; load value from variable
  %temp2 = load i32, i32* %xPtr
  ; call to runtime function to output a value
  call void @t_rt_print_int(i32 %temp2)
  ; load value from variable
  %temp3 = load i32, i32* %xPtr
  ; negate
  %temp4 = sub i32 0, 1
  ; add
  %temp5 = add i32 %temp3, %temp4
  ; store assigned value
  store i32 %temp5, i32* %xPtr
  br label %label0
  ; end while statement body
  label1:
  ret void
}
