package com.myapp.hostel_management_system.service;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.repository.HostelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelService {
    private final HostelRepository hostelRepository;

    public HostelService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public int countAllStudents(Hostel hostel) {
        return hostelRepository.countAllByStudents(hostel);
    }

    public List<Hostel> getAllHostel() {
        return hostelRepository.findAll();
    }

    public void hostelSave(Hostel hostel) {
        hostelRepository.save(hostel);
    }

    public Hostel getHostelById(String id) {
        return hostelRepository.findById(id).get();
    }

    public void updateHostel(Hostel hostel) {
        hostelRepository.save(hostel);
    }

    public void deleteHostelById(String id) {
        hostelRepository.deleteById(id);
    }
}
