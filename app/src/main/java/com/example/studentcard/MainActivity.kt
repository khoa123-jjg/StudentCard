package com.example.studentcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentcard.databinding.ActivityMainBinding // Lệnh import ViewBinding quan trọng!
import com.example.studentcard.model.Student
import com.example.studentcard.utils.toAcademicRanking
import com.example.studentcard.utils.toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "2415053122221",
        name = "Phạm Nhật Khoa",
        className = "24T2",
        email = "2415053122221@sv.ute.udn.vn",
        gpa = 3.4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindStudentData(currentStudent)

        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            // Cập nhật dữ liệu bằng hàm copy() và vẽ lại UI
            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent)
            toast("Cập nhật điểm thành công!")
        }
    }

    // Hàm gán dữ liệu lên các View bằng scope function 'with'
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}